package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.pixels.timber.TimberModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [TimberModule::class])
@InstallIn(ApplicationComponent::class)
object ApplicationModule
