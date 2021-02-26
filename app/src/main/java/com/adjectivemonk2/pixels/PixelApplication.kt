package com.adjectivemonk2.pixels

import android.app.Application
import com.adjectivemonk2.pixels.di.DaggerApplicationComponent
import com.adjectivemonk2.pixels.timber.LoggingTree
import timber.log.Timber
import timber.log.verbose
import javax.inject.Inject

class PixelApplication : Application() {

  private val applicationComponent by lazy(LazyThreadSafetyMode.NONE) {
    DaggerApplicationComponent.builder().application(this).build()
  }

  @Inject internal lateinit var tree: LoggingTree

  override fun onCreate() {
    applicationComponent.inject(this)
    super.onCreate()
    Timber.plant(tree)

    Timber.verbose { "onCreate" }
  }
}
