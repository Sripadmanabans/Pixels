package com.adjectivemonk2.pixels.logger.impl

import com.squareup.workflow1.SimpleLoggingWorkflowInterceptor
import javax.inject.Inject
import logcat.LogPriority.ERROR
import logcat.LogPriority.VERBOSE
import logcat.logcat

public class LoggingWorkflowInterceptor @Inject constructor() : SimpleLoggingWorkflowInterceptor() {

  public override fun log(text: String) {
    logcat(tag = TAG, priority = VERBOSE) { text }
  }

  public override fun logError(text: String) {
    logcat(tag = TAG, priority = ERROR) { text }
  }

  private companion object {
    private const val TAG = "Workflow"
  }
}
