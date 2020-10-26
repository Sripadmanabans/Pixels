package com.adjectivemonk2.pixels.timber

import dagger.Binds
import dagger.Module
import timber.log.Tree

@Module
public abstract class TimberModule {

  @Binds internal abstract fun tree(tree: LoggingTree): Tree
}
