package com.adjectivemonk2.network.core.impl.serialization

import kotlinx.serialization.SerializationStrategy
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter

internal class SerializationStrategyConverter<T>(
  private val contentType: MediaType,
  private val saver: SerializationStrategy<T>,
  private val serializer: Serializer
) : Converter<T, RequestBody> {
  override fun convert(value: T): RequestBody {
    return serializer.toRequestBody(contentType, saver, value)
  }
}
