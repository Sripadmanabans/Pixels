package com.adjectivemonk2.pixels.network.gallery.impl.wiring

import com.adjectivemonk2.pixels.network.core.ApiRetrofit
import com.adjectivemonk2.pixels.network.gallery.impl.GalleryService
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
@ContributesTo(AppScope::class)
public object GalleryModule {

  @Provides
  @SingleIn(AppScope::class)
  public fun galleryService(
    @ApiRetrofit retrofit: Retrofit,
  ): GalleryService {
    return retrofit.create()
  }
}
