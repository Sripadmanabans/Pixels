package com.adjectivemonk2.pixels

import android.app.Activity
import android.app.Application
import com.adjectivemonk2.pixels.di.AppComponent
import timber.log.Timber
import javax.inject.Inject

class PixelApplication : Application() {

  val appComponent by lazy(LazyThreadSafetyMode.NONE) {
    AppComponent.builder().application(this).build()
  }

  @Inject internal lateinit var tree: Timber.Tree

  override fun onCreate() {
    appComponent.injectInTo(this)
    super.onCreate()
    Timber.plant(tree)

    Timber.v("onCreate")
  }
}

val Activity.appComponent: AppComponent
  get() {
    return (application as PixelApplication).appComponent
  }
