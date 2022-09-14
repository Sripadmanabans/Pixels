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
public interface AppComponent : Injector<PixelApplication> {

  public fun activityComponent(): ActivityComponent

  @Component.Factory
  public interface Factory {
    public fun create(@BindsInstance application: Application): AppComponent
  }

  public companion object {
    public fun factory(): Factory = DaggerAppComponent.factory()
  }
}
