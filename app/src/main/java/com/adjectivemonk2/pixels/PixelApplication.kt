package com.adjectivemonk2.pixels

import android.app.Activity
import android.app.Application
import com.adjectivemonk2.pixels.di.AppComponent
import com.adjectivemonk2.pixels.timber.LoggingTree
import timber.log.Timber
import timber.log.verbose
import javax.inject.Inject

class PixelApplication : Application() {

  val appComponent by lazy(LazyThreadSafetyMode.NONE) {
    AppComponent.builder().application(this).build()
  }

  @Inject internal lateinit var tree: LoggingTree

  override fun onCreate() {
    appComponent.injectInTo(this)
    super.onCreate()
    Timber.plant(tree)

    Timber.verbose { "onCreate" }
  }
}

val Activity.appComponent: AppComponent
  get() {
    return (application as PixelApplication).appComponent
  }
