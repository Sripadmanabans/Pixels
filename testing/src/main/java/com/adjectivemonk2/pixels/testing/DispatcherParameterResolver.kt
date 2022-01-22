package com.adjectivemonk2.pixels.testing

import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

public class DispatcherParameterResolver :
  ParameterResolver,
  BeforeEachCallback,
  AfterEachCallback {

  private var dispatcher: TestDispatcher? = null

  override fun supportsParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext?
  ): Boolean {
    return parameterContext.parameter.type == TestDispatcher::class.java
  }

  override fun resolveParameter(
    parameterContext: ParameterContext?,
    extensionContext: ExtensionContext?
  ): Any {
    return dispatcher!!
  }

  override fun beforeEach(context: ExtensionContext?) {
    dispatcher = StandardTestDispatcher()
  }

  override fun afterEach(context: ExtensionContext?) {
    dispatcher!!.cancel()
    dispatcher = null
  }
}
