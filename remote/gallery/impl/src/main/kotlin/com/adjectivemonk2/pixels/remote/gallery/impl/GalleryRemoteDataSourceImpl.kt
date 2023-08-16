package com.adjectivemonk2.pixels.remote.gallery.impl

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.remote.gallery.GalleryRemoteDataSource
import com.adjectivemonk2.pixels.remote.gallery.impl.converter.GalleryConverter
import com.adjectivemonk2.pixels.remote.model.core.Data
import com.adjectivemonk2.pixels.remote.model.gallery.GalleryFromRemote
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class GalleryRemoteDataSourceImpl @Inject constructor(
  private val client: HttpClient,
  private val galleryConverter: GalleryConverter,
) : GalleryRemoteDataSource {
  override suspend fun getGallery(
    section: String,
    sort: String,
    window: String,
    page: Int,
  ): List<Gallery> {
    return client.get("gallery/$section/$sort/$window/$page")
      .body<Data<List<GalleryFromRemote>>>()
      .data
      .map { galleryConverter.convert(it) }
  }
}
