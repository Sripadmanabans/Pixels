package com.adjectivemonk2.pixels.testing

import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

public class DispatcherParameterResolver :
  ParameterResolver,
  BeforeEachCallback,
  AfterEachCallback {

  private var dispatcher: TestCoroutineDispatcher? = null

  override fun supportsParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext?
  ): Boolean {
    return parameterContext.parameter.type == TestCoroutineDispatcher::class.java
  }

  override fun resolveParameter(
    parameterContext: ParameterContext?,
    extensionContext: ExtensionContext?
  ): Any {
    println("ResolveParameter: ${dispatcher!!.hashCode()}")
    return dispatcher!!
  }

  override fun beforeEach(context: ExtensionContext?) {
    dispatcher = TestCoroutineDispatcher()
    println("beforeEach: ${dispatcher!!.hashCode()}")
  }

  override fun afterEach(context: ExtensionContext?) {
    println("afterEach: ${dispatcher!!.hashCode()}")
    dispatcher!!.cancel()
    dispatcher = null
  }
}
