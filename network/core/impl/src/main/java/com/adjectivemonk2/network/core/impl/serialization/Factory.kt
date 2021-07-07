package com.adjectivemonk2.network.core.impl.serialization

import kotlinx.serialization.StringFormat
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class Factory(
  private val contentType: MediaType,
  private val serializer: Serializer
) : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit
  ): Converter<ResponseBody, *> {
    val loader = serializer.getSerializer(type)
    return DeserializationStrategyConverter(loader, serializer)
  }

  override fun requestBodyConverter(
    type: Type,
    parameterAnnotations: Array<out Annotation>,
    methodAnnotations: Array<out Annotation>,
    retrofit: Retrofit
  ): Converter<*, RequestBody> {
    val saver = serializer.getSerializer(type)
    return SerializationStrategyConverter(contentType, saver, serializer)
  }
}

public fun StringFormat.asConverterFactory(contentType: MediaType): Converter.Factory {
  return Factory(contentType, Serializer.FromString(this))
}
