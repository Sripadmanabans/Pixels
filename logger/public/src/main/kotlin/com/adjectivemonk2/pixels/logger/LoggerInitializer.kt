package com.adjectivemonk2.pixels.logger

import android.app.Application
import android.content.pm.ApplicationInfo
import logcat.LogcatLogger

public object LoggerInitializer {

  public fun installOnDebuggableApp(
    application: Application,
    logcatLogger: LogcatLogger,
  ) {
    if (!LogcatLogger.isInstalled && application.isDebuggableApp) {
      LogcatLogger.install(logcatLogger)
    }
  }

  private val Application.isDebuggableApp: Boolean
    get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
}
