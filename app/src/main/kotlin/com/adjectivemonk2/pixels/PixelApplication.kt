package com.adjectivemonk2.pixels

import android.app.Application
import com.adjectivemonk2.pixels.di.ActivityComponent
import com.adjectivemonk2.pixels.local.PixelsDb
import com.adjectivemonk2.pixels.logger.LoggerInitializer
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import logcat.LogcatLogger
import logcat.logcat
import javax.inject.Inject
import javax.inject.Provider

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class PixelApplication @Inject constructor(
  private val logger: LogcatLogger,
  private val pixelsDb: Provider<PixelsDb>,
  public val activityComponentFactory: ActivityComponent.Factory,
) : Application() {

  override fun onCreate() {
    super.onCreate()
    LoggerInitializer.installOnDebuggableApp(this, logger)

    logcat { "onCreate" }
    pixelsDb.get().versionQueries
      .insert(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE.toLong())
  }
}
