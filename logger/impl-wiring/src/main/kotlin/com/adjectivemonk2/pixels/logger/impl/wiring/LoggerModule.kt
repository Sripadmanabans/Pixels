package com.adjectivemonk2.pixels.logger.impl.wiring

import com.adjectivemonk2.pixels.logger.impl.LoggingWorkflowInterceptor
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.workflow1.WorkflowInterceptor
import dagger.Module
import dagger.Provides
import logcat.AndroidLogcatLogger

@ContributesTo(AppScope::class)
@Module
public class LoggerModule {

  @Provides public fun workflowInterceptors(
    loggingWorkflowInterceptor: LoggingWorkflowInterceptor,
  ): List<WorkflowInterceptor> {
    return listOf(loggingWorkflowInterceptor)
  }

  @Provides public fun androidLogcatLogger(): AndroidLogcatLogger = AndroidLogcatLogger()
}
