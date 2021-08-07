package com.adjectivemonk2.pixels.network.core.impl.wiring

import com.adjectivemonk2.pixels.network.core.ApiRetrofit
import com.adjectivemonk2.pixels.network.core.impl.converter.DataConverterFactory
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@ContributesTo(AppScope::class)
public object NetworkModule {

  @Provides public fun moshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides public fun moshiConverterFactory(moshi: Moshi): Converter.Factory {
    return MoshiConverterFactory.create(moshi)
  }

  @Provides @SingleIn(AppScope::class) @ApiRetrofit public fun retrofit(
    okHttpClient: OkHttpClient,
    baseUrl: HttpUrl,
    dataConverter: DataConverterFactory,
    moshiConverter: Converter.Factory,
  ): Retrofit {
    return Retrofit.Builder().run {
      client(okHttpClient)
      baseUrl(baseUrl)
      addConverterFactory(dataConverter)
      addConverterFactory(moshiConverter)
      build()
    }
  }
}
