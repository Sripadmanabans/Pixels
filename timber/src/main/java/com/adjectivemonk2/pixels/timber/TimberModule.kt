package com.adjectivemonk2.pixels.timber

import dagger.Binds
import dagger.Module
import timber.log.Tree

@Module
abstract class TimberModule {

  @Binds abstract fun tree(tree: LoggingTree): Tree
}
