package com.adjectivemonk2.pixels

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.annotation.Keep
import androidx.core.app.AppComponentFactory
import com.adjectivemonk2.pixels.di.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Provider

@Keep
public class PixelComponentFactory : AppComponentFactory() {

  private val appComponent = DaggerAppComponent.create()

  @Inject internal lateinit var application: Provider<PixelApplication>

  init {
    appComponent.injectInto(this)
  }

  override fun instantiateApplicationCompat(cl: ClassLoader, className: String): Application {
    return application.get()
  }

  override fun instantiateActivityCompat(
    cl: ClassLoader,
    className: String,
    intent: Intent?,
  ): Activity {
    val clazz = Class.forName(className, false, cl)
    return if (clazz == PixelActivity::class.java) {
      application.get().activityComponentFactory.create().activity
    } else {
      super.instantiateActivityCompat(cl, className, intent)
    }
  }
}
