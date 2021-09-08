package com.adjectivemonk2.pixels.di

import android.app.Application
import com.adjectivemonk2.pixels.PixelApplication
import com.adjectivemonk2.pixels.inject.Injector
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component

@SingleIn(AppScope::class)
@MergeComponent(scope = AppScope::class)
interface AppComponent : Injector<PixelApplication> {

  fun activityComponent(): ActivityComponent

  @Component.Builder
  interface Builder {
    fun application(@BindsInstance application: Application): Builder

    fun build(): AppComponent
  }

  companion object {
    fun builder(): Builder = DaggerAppComponent.builder()
  }
}
