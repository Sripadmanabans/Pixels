package com.adjectivemonk2.pixels.dispatcher.impl.wiring

import com.adjectivemonk2.pixels.dispatcher.DefaultDispatcher
import com.adjectivemonk2.pixels.dispatcher.IoDispatcher
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@ContributesTo(AppScope::class)
public object DispatcherModule {

  @Provides @IoDispatcher public fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides @DefaultDispatcher public fun defaultDispatcher(): CoroutineDispatcher {
    return Dispatchers.Default
  }
}
