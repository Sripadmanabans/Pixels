package com.adjectivemonk2.pixels.di

import android.app.Application
import com.adjectivemonk2.pixels.PixelApplication
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module

@Module
@ContributesTo(AppScope::class)
abstract class AppModule {
  @Binds abstract fun application(application: PixelApplication): Application
}
