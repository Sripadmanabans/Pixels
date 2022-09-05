package com.adjectivemonk2.pixels.network.core.impl.converter

import com.adjectivemonk2.pixels.model.core.Data
import com.squareup.moshi.Types
import java.lang.reflect.Type
import javax.inject.Inject
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

public class DataConverterFactory @Inject constructor() : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit,
  ): Converter<ResponseBody, *> {
    val envelopedType = Types.newParameterizedType(Data::class.java, type)
    val delegate = retrofit.nextResponseBodyConverter<Data<*>>(
      this,
      envelopedType,
      annotations,
    )
    return DelegateConverter(delegate)
  }

  private class DelegateConverter(
    private val delegate: Converter<ResponseBody, Data<*>>,
  ) : Converter<ResponseBody, Any> {
    override fun convert(value: ResponseBody): Any? {
      return delegate.convert(value)?.data
    }
  }
}
