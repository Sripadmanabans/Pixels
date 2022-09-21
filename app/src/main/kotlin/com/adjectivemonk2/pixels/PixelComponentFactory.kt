package com.adjectivemonk2.pixels

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.core.app.AppComponentFactory
import com.adjectivemonk2.pixels.di.ActivityComponent
import com.adjectivemonk2.pixels.di.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Provider

public class PixelComponentFactory : AppComponentFactory() {

  private val appComponent = DaggerAppComponent.create()

  @Inject internal lateinit var application: Provider<PixelApplication>

  @Inject internal lateinit var activityComponentFactory: ActivityComponent.Factory

  init {
    appComponent.injectInTo(this)
  }

  override fun instantiateApplicationCompat(cl: ClassLoader, className: String): Application {
    val clazz = Class.forName(className, false, cl)
    return if (clazz == PixelApplication::class.java) {
      application.get()
    } else {
      super.instantiateApplicationCompat(cl, className)
    }
  }

  override fun instantiateActivityCompat(
    cl: ClassLoader,
    className: String,
    intent: Intent?,
  ): Activity {
    val clazz = Class.forName(className, false, cl)
    return if (clazz == PixelActivity::class.java) {
      activityComponentFactory.create().activity
    } else {
      super.instantiateActivityCompat(cl, className, intent)
    }
  }
}
