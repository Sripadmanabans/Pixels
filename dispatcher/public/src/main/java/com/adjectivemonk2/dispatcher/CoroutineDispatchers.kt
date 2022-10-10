package com.adjectivemonk2.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

public class CoroutineDispatchers(
  public val default: CoroutineDispatcher,
  public val io: CoroutineDispatcher,
  public val main: CoroutineDispatcher,
) {
  @Inject
  public constructor() : this(Dispatchers.Default, Dispatchers.IO, Dispatchers.Main)
}
