package com.adjectivemonk2.pixels.remote.core.impl.wiring

import android.app.Application
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import java.io.File
import co.touchlab.kermit.Logger.Companion as KermitLogger

@Module
@ContributesTo(AppScope::class)
public object RemoteModule {

  @Provides
  @SingleIn(AppScope::class)
  public fun cacheDir(application: Application): File {
    return File(application.cacheDir, HTTP)
  }

  @OptIn(ExperimentalSerializationApi::class)
  @Provides
  @SingleIn(AppScope::class)
  public fun client(
    cacheDir: File,
    serializersModules: Set<@JvmSuppressWildcards SerializersModule>,
  ): HttpClient {
    return HttpClient(CIO) {
      install(HttpCache) {
        publicStorage(FileStorage(cacheDir))
      }

      install(Logging) {
        logger = object : Logger {
          private val _logger by lazy(LazyThreadSafetyMode.NONE) {
            KermitLogger.withTag("HTTP")
          }

          override fun log(message: String) {
            _logger.v { message }
          }
        }
        level = LogLevel.BODY
      }

      install(ContentNegotiation) {
        json(
          Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            serializersModule = serializersModules.fold(serializersModule) { acc, module ->
              acc + module
            }
          },
        )
      }

      defaultRequest {
        url("https://api.imgur.com/3/")
        header("Authorization", "Client-ID $clientId")
      }
    }
  }

  private val clientId by lazy(LazyThreadSafetyMode.NONE) {
    val pixelClientId = BuildConfig.PIXELS_CLIENT_ID
    check(pixelClientId.isNotBlank()) {
      "We need to provide a value for PIXEL_CLIENT_ID in gradle"
    }
    pixelClientId
  }

  private const val HTTP = "http"
}
