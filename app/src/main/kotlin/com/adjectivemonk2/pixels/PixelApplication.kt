package com.adjectivemonk2.pixels

import android.app.Activity
import android.app.Application
import com.adjectivemonk2.pixels.di.AppComponent
import com.adjectivemonk2.pixels.logger.LoggerInitializer
import logcat.LogcatLogger
import logcat.logcat
import javax.inject.Inject

public class PixelApplication : Application() {

  public val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
    AppComponent.factory().create(this)
  }

  @Inject internal lateinit var logger: LogcatLogger

  override fun onCreate() {
    appComponent.injectInTo(this)
    super.onCreate()
    LoggerInitializer.installOnDebuggableApp(this, logger)

    logcat { "onCreate" }
  }
}

public val Activity.appComponent: AppComponent
  get() {
    return (application as PixelApplication).appComponent
  }
