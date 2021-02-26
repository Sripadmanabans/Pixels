package com.adjectivemonk2.pixels.timber.impl.wiring

import com.adjectivemonk2.pixels.dagger.scope.AppScope
import com.adjectivemonk2.pixels.timber.LoggingTree
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(AppScope::class)
public interface TimberComponent {
  public fun loggingTree(): LoggingTree
}
