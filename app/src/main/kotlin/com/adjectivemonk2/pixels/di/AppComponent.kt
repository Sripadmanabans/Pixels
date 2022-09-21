package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.pixels.PixelComponentFactory
import com.adjectivemonk2.pixels.inject.Injector
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.MergeComponent

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
public interface AppComponent : Injector<PixelComponentFactory>
