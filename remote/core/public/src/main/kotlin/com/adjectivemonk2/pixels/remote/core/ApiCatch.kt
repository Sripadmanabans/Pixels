package com.adjectivemonk2.pixels.remote.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import java.io.IOException

public fun <T> Flow<T>.apiCatch(
  action: suspend FlowCollector<T>.(cause: Throwable) -> Unit,
): Flow<T> {
  return catch {
    if (it is IOException) action(it) else throw it
  }
}
