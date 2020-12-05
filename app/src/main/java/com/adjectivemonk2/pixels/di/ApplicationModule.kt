package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.pixels.timber.TimberModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [TimberModule::class])
@InstallIn(SingletonComponent::class)
object ApplicationModule
