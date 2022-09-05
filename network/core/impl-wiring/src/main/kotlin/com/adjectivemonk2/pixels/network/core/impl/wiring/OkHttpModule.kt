package com.adjectivemonk2.pixels.network.core.impl.wiring

import android.app.Application
import com.adjectivemonk2.pixels.network.core.impl.interceptor.AuthInterceptor
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.jakewharton.byteunits.BinaryByteUnit
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import java.io.File
import logcat.logcat
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@ContributesTo(AppScope::class)
public object OkHttpModule {

  @Provides
  @SingleIn(AppScope::class)
  public fun cacheDir(application: Application): File {
    return File(application.cacheDir, HTTP)
  }

  @Provides
  public fun cache(cacheDir: File): Cache {
    return Cache(cacheDir, BinaryByteUnit.MEBIBYTES.toBytes(CACHE_SIZE_MB))
  }

  @Provides
  public fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor { logcat(HTTP) { it } }
      .apply { level = HttpLoggingInterceptor.Level.BASIC }
  }

  @Provides
  @SingleIn(AppScope::class)
  public fun okHttp(
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

  private const val CACHE_SIZE_MB = 10L
  private const val HTTP = "http"
}
