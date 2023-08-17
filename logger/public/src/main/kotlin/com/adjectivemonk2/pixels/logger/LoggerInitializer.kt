package com.adjectivemonk2.pixels.logger

import android.app.Application
import android.content.pm.ApplicationInfo
import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger

public object LoggerInitializer {

  public fun installOnDebuggableApp(
    application: Application,
    logcatLoggerFactory: () -> LogWriter,
  ) {
    if (application.isDebuggableApp) {
      Logger.setTag("Pixels")
      Logger.setLogWriters(logcatLoggerFactory())
    }
  }

  private val Application.isDebuggableApp: Boolean
    get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
}
