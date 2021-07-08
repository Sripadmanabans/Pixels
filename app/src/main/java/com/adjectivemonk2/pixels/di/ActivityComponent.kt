package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.inject.Injector
import com.adjectivemonk2.pixels.PixelActivity
import com.adjectivemonk2.scope.ActivityScope
import com.adjectivemonk2.scope.SingleIn
import com.squareup.anvil.annotations.MergeSubcomponent

@SingleIn(ActivityScope::class)
@MergeSubcomponent(ActivityScope::class)
interface ActivityComponent : Injector<PixelActivity>
