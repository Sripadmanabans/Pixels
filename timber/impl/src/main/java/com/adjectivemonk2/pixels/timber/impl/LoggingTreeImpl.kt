package com.adjectivemonk2.pixels.timber.impl

import android.util.Log
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.timber.LoggingTree
import com.squareup.anvil.annotations.ContributesBinding
import java.io.PrintWriter
import java.io.StringWriter
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject
import kotlin.math.min

@ContributesBinding(scope = AppScope::class, boundType = LoggingTree::class)
public class LoggingTreeImpl @Inject constructor() : LoggingTree() {

  override fun performLog(priority: Int, tag: String?, throwable: Throwable?, message: String?) {
    val tempMessage = if (message == null) {
      if (throwable != null) {
        getStackTraceString(throwable)
      } else {
        null
      }
    } else {
      if (throwable != null) {
        "$message\n${getStackTraceString(throwable)}"
      } else {
        message
      }
    } ?: return

    val finalMessage = "{${Thread.currentThread().name}} $tempMessage"

    // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
    // because Robolectric runs them on the JVM but on Android the elements are different.
    val stackTrace = Throwable("Creating tag").stackTrace
    check(stackTrace.size > CALL_STACK_INDEX) {
      "Synthetic stacktrace didn't have enough elements: are you using proguard?"
    }
    val elementTag = tag ?: createStackElementTag(stackTrace[CALL_STACK_INDEX])
    if (finalMessage.length < MAX_LOG_LENGTH) {
      if (priority == Log.ASSERT) {
        Log.wtf(elementTag, finalMessage)
      } else {
        Log.println(priority, elementTag, finalMessage)
      }
      return
    }

    // Split by line, then ensure each line can fit into Log's maximum length.
    val length = finalMessage.length
    var i = 0
    while (i < length) {
      var newline = finalMessage.indexOf('\n', i)
      newline = if (newline != -1) newline else length
      do {
        val end = min(newline, i + MAX_LOG_LENGTH)
        val part = finalMessage.substring(i, end)
        if (priority == Log.ASSERT) {
          Log.wtf(elementTag, part)
        } else {
          Log.println(priority, elementTag, part)
        }
        i = end
      } while (i < newline)
      i++
    }
  }

  private fun getStackTraceString(t: Throwable): String {
    // Don't replace this with Log.getStackTraceString() - it hides
    // UnknownHostException, which is not what we want.
    val sw = StringWriter(STACK_TRACE_INITIAL_BUFFER)
    val pw = PrintWriter(sw, false)
    t.printStackTrace(pw)
    pw.flush()
    return sw.toString()
  }

  private fun createStackElementTag(element: StackTraceElement): String {
    var tag = element.className
    val matcher: Matcher = ANONYMOUS_CLASS.matcher(tag)
    if (matcher.find()) {
      tag = matcher.replaceAll("")
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1)
    return if (tag.length <= MAX_TAG_LENGTH) {
      tag
    } else {
      tag.substring(0, MAX_TAG_LENGTH)
    }
  }

  private companion object {
    private const val STACK_TRACE_INITIAL_BUFFER = 256
    private const val MAX_LOG_LENGTH = 4000
    private const val MAX_TAG_LENGTH = 23
    private const val CALL_STACK_INDEX = 3
    private val ANONYMOUS_CLASS: Pattern = Pattern.compile("(\\$\\d+)+$")
  }
}
