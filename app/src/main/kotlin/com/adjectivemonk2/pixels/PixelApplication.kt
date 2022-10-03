package com.adjectivemonk2.pixels

import android.app.Application
import com.adjectivemonk2.pixels.di.ActivityComponent
import com.adjectivemonk2.pixels.logger.LoggerInitializer
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import logcat.LogcatLogger
import logcat.logcat
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class PixelApplication @Inject constructor(
  private val logger: LogcatLogger,
  public val activityComponentFactory: ActivityComponent.Factory,
) : Application() {

  override fun onCreate() {
    super.onCreate()
    LoggerInitializer.installOnDebuggableApp(this, logger)

    logcat { "onCreate" }
  }
}
