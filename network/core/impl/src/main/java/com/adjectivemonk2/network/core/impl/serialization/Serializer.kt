package com.adjectivemonk2.network.core.impl.serialization

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.lang.reflect.Type

internal sealed class Serializer {
  abstract fun <T> fromResponseBody(loader: DeserializationStrategy<T>, body: ResponseBody): T
  abstract fun <T> toRequestBody(
    contentType: MediaType,
    saver: SerializationStrategy<T>,
    value: T
  ): RequestBody

  protected abstract val format: SerialFormat

  fun getSerializer(type: Type): KSerializer<Any> = format.serializersModule.serializer(type)

  class FromString(override val format: StringFormat) : Serializer() {
    override fun <T> fromResponseBody(loader: DeserializationStrategy<T>, body: ResponseBody): T {
      val string = body.string()
      return format.decodeFromString(loader, string)
    }

    override fun <T> toRequestBody(
      contentType: MediaType,
      saver: SerializationStrategy<T>,
      value: T
    ): RequestBody {
      val string = format.encodeToString(saver, value)
      return string.toRequestBody(contentType)
    }
  }
}
