package com.adjectivemonk2.pixels.network.core.impl.wiring

import android.app.Application
import com.adjectivemonk2.pixels.network.core.ApiRetrofit
import com.adjectivemonk2.pixels.network.core.impl.interceptor.AuthInterceptor
import com.adjectivemonk2.pixels.network.core.impl.serialization.asConverterFactory
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.jakewharton.byteunits.BinaryByteUnit.MEBIBYTES
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import timber.log.debug
import java.io.File

@Module
@ContributesTo(AppScope::class)
public object NetworkModule {

  @Provides public fun cacheDir(application: Application): File {
    return File(application.cacheDir, "http")
  }

  @Provides @SingleIn(AppScope::class) public fun cache(cacheDir: File): Cache {
    return Cache(cacheDir, MEBIBYTES.toBytes(CACHE_SIZE_MB))
  }

  @Provides public fun loggingInterceptor(): HttpLoggingInterceptor {
    val logger = Timber.tagged("http")
    return HttpLoggingInterceptor { logger.debug { it } }
      .apply { level = HttpLoggingInterceptor.Level.BASIC }
  }

  @Provides @SingleIn(AppScope::class) public fun okHttp(
    cache: Cache,
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient {
    return OkHttpClient.Builder().run {
      cache(cache)
      addInterceptor(authInterceptor)
      addInterceptor(loggingInterceptor)
      build()
    }
  }

  @Provides public fun baseUrl(): HttpUrl {
    return "https://api.imgur.com/3/".toHttpUrl()
  }

  @Provides @SingleIn(AppScope::class) public fun json(): Json {
    return Json {
      ignoreUnknownKeys = true
    }
  }

  @Provides @SingleIn(AppScope::class) public fun converterFactory(json: Json): Converter.Factory {
    val contentType = "application/json".toMediaType()
    return json.asConverterFactory(contentType)
  }

  @Provides @SingleIn(AppScope::class) @ApiRetrofit public fun retrofit(
    okHttpClient: OkHttpClient,
    baseUrl: HttpUrl,
    converter: Converter.Factory,
  ): Retrofit {
    return Retrofit.Builder().run {
      client(okHttpClient)
      baseUrl(baseUrl)
      addConverterFactory(converter)
      build()
    }
  }

  private const val CACHE_SIZE_MB = 10L
}
