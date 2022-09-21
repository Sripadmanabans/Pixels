package com.adjectivemonk2.pixels.testing

import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.ExtensionContext.Store
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

public class DispatcherParameterResolver : ParameterResolver {

  private class DispatcherProvider : Store.CloseableResource {
    val dispatcher = StandardTestDispatcher()
    override fun close() {
      dispatcher.cancel()
    }
  }

  override fun supportsParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext,
  ): Boolean {
    return parameterContext.parameter.type == TestDispatcher::class.java
  }

  override fun resolveParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext,
  ): Any {
    val dispatcherProvider = extensionContext.store
      .getOrComputeIfAbsentInline(extensionContext.uniqueId) { DispatcherProvider() }
    return dispatcherProvider.dispatcher
  }

  private val ExtensionContext.store: Store
    get() = getStore(Namespace.create(testClass, requiredTestMethod))

  private inline fun <Key, reified Value> Store.getOrComputeIfAbsentInline(
    key: Key,
    crossinline defaultCreator: (Key) -> Value,
  ): Value {
    return getOrComputeIfAbsent(key, { defaultCreator(it) }, Value::class.java)
  }
}
