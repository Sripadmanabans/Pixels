package com.adjectivemonk2.pixels.remote.gallery

import com.adjectivemonk2.pixels.model.gallery.Gallery

public interface GalleryRemoteDataSource {
  public suspend fun getGallery(
    section: String,
    sort: String,
    window: String,
    page: Int,
  ): List<Gallery>
}
