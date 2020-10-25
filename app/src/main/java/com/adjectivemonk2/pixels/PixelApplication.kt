package com.adjectivemonk2.pixels

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Tree
import timber.log.verbose
import javax.inject.Inject

@HiltAndroidApp
class PixelApplication : Application() {

  @Inject internal lateinit var tree: Tree

  override fun onCreate() {
    super.onCreate()
    Timber.plant(tree)

    Timber.verbose { "onCreate" }
  }
}
