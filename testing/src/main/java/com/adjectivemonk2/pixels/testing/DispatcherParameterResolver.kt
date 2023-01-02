package com.adjectivemonk2.pixels.testing

import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.ExtensionContext.Store
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

public class DispatcherParameterResolver :
  ParameterResolver,
  BeforeEachCallback,
  AfterEachCallback {

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
    return extensionContext.store[dispatcherKey]
  }

  override fun beforeEach(context: ExtensionContext) {
    context.store.put(dispatcherKey, StandardTestDispatcher())
  }

  override fun afterEach(context: ExtensionContext) {
    context.store.get(dispatcherKey, TestDispatcher::class.java)!!.cancel()
  }

  private val ExtensionContext.store: Store
    get() = getStore(Namespace.create(testClass, requiredTestMethod))

  private companion object {
    val dispatcherKey = requireNotNull(TestDispatcher::class.qualifiedName)
  }
}
