package com.adjectivemonk2.pixels.remote.gallery.impl

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.remote.gallery.GalleryRemoteDataSource
import com.adjectivemonk2.pixels.remote.gallery.impl.converter.GalleryConverter
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class GalleryRemoteDataSourceImpl @Inject constructor(
  private val galleryService: GalleryService,
  private val galleryConverter: GalleryConverter,
) : GalleryRemoteDataSource {
  override suspend fun getGallery(
    section: String,
    sort: String,
    window: String,
    page: Int,
  ): List<Gallery> {
    return galleryService.getGallery(section, sort, window, page)
      .map { galleryConverter.convert(it) }
  }
}
