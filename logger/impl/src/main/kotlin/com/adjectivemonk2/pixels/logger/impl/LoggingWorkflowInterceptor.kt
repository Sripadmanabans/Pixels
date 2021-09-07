package com.adjectivemonk2.pixels.logger.impl

import com.squareup.workflow1.SimpleLoggingWorkflowInterceptor
import timber.log.Timber
import javax.inject.Inject

public class LoggingWorkflowInterceptor @Inject constructor() : SimpleLoggingWorkflowInterceptor() {

  public override fun log(text: String) {
    Timber.tag("Workflow").v(text)
  }

  public override fun logError(text: String) {
    Timber.tag("Workflow").e(text)
  }
}
