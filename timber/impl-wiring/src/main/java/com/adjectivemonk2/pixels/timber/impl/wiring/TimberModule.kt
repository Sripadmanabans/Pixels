package com.adjectivemonk2.pixels.timber.impl.wiring

import com.adjectivemonk2.pixels.timber.LoggingTree
import com.adjectivemonk2.pixels.timber.impl.LoggingTreeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
public abstract class TimberModule {

  @Binds
  public abstract fun loggingTree(tree: LoggingTreeImpl): LoggingTree
}
