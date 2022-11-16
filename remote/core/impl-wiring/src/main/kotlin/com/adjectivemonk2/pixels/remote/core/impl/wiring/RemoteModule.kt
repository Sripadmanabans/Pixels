package com.adjectivemonk2.pixels.remote.core.impl.wiring

import com.adjectivemonk2.pixels.remote.core.ApiRetrofit
import com.adjectivemonk2.pixels.remote.core.impl.adapter.InstantAdapter
import com.adjectivemonk2.pixels.remote.core.impl.converter.DataConverterFactory
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
public object RemoteModule {

  @Provides
  public fun moshi(instantAdapter: InstantAdapter): Moshi {
    return Moshi.Builder().add(instantAdapter).build()
  }

  @Provides
  public fun moshiConverterFactory(moshi: Moshi): Converter.Factory {
    return MoshiConverterFactory.create(moshi)
  }

  @Provides
  @SingleIn(AppScope::class)
  @ApiRetrofit
  public fun retrofit(
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
