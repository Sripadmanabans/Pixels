package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.pixels.PixelApplication
import com.adjectivemonk2.pixels.dagger.scope.AppScope
import com.adjectivemonk2.pixels.dagger.scope.Injector
import com.adjectivemonk2.pixels.dagger.scope.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component

@SingleIn(AppScope::class)
@MergeComponent(
  scope = AppScope::class,
  modules = [SqlModule::class]
)
interface ApplicationComponent : Injector<PixelApplication> {

  @Component.Builder
  interface Builder {
    fun application(@BindsInstance application: PixelApplication): Builder

    fun build(): ApplicationComponent
  }
}
