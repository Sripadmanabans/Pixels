package com.adjectivemonk2.network.gallery.impl.wiring

import com.adjectivemonk2.network.core.ApiRetrofit
import com.adjectivemonk2.network.gallery.GalleryRepository
import com.adjectivemonk2.network.gallery.impl.GalleryRepositoryImpl
import com.adjectivemonk2.network.gallery.impl.GalleryService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public abstract class GalleryModule {

  @Binds public abstract fun galleryRepository(repository: GalleryRepositoryImpl): GalleryRepository

  public companion object {
    @Provides @Singleton public fun galleryService(
      @ApiRetrofit retrofit: Retrofit,
    ): GalleryService {
      return retrofit.create()
    }
  }
}
