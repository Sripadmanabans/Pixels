package com.adjectivemonk2.pixels

import android.app.Application
import com.adjectivemonk2.pixels.timber.LoggingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.verbose
import javax.inject.Inject

@HiltAndroidApp
class PixelApplication : Application() {

  @Inject internal lateinit var tree: LoggingTree

  override fun onCreate() {
    super.onCreate()
    Timber.plant(tree)

    Timber.verbose { "onCreate" }
  }
}
