package com.adjectivemonk2.pixels.network.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.io.IOException

public fun <T> Flow<T>.apiCatch(action: suspend FlowCollector<T>.(cause: Throwable) -> Unit): Flow<T> {
  return catch {
    if (it is IOException || it is HttpException) action(it) else throw it
  }
}
