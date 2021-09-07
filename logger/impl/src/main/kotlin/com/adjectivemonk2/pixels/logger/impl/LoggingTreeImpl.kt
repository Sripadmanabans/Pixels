package com.adjectivemonk2.pixels.logger.impl

import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import timber.log.Timber
import javax.inject.Inject

@ContributesBinding(scope = AppScope::class, boundType = Timber.Tree::class)
public class LoggingTreeImpl @Inject constructor() : Timber.DebugTree() {

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    val finalMessage = "{${Thread.currentThread().name}} $message"
    super.log(priority, tag, finalMessage, t)
  }
}
