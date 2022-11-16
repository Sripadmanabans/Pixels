package com.adjectivemonk2.pixels.remote.core.impl.interceptor

import com.adjectivemonk2.pixels.remote.core.impl.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

public class AuthInterceptor @Inject constructor() : Interceptor {

  private val clientId by lazy(LazyThreadSafetyMode.NONE) {
    val pixelClientId = BuildConfig.PIXELS_CLIENT_ID
    check(pixelClientId.isNotBlank()) {
      "We need to provide a value for PIXEL_CLIENT_ID in gradle"
    }
    pixelClientId
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    val newRequest = chain.request().newBuilder().run {
      addHeader("Authorization", "Client-ID $clientId")
      build()
    }
    return chain.proceed(newRequest)
  }
}
