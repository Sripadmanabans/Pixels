package com.adjectivemonk2.pixels.logger.impl

import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import logcat.LogcatLogger
import javax.inject.Inject

@ContributesBinding(scope = AppScope::class)
public class LogcatLoggerImpl @Inject constructor(
  private val delegate: AndroidLogcatLogger,
) : LogcatLogger by delegate {

  override fun log(priority: LogPriority, tag: String, message: String) {
    val finalMessage = "{${Thread.currentThread().name}} $message"
    delegate.log(priority, tag, finalMessage)
  }
}
