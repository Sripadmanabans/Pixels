package com.adjectivemonk2.network.gallery.impl

import com.adjectivemonk2.network.gallery.GalleryRepository
import com.adjectivemonk2.network.gallery.Section
import com.adjectivemonk2.network.gallery.Sort
import com.adjectivemonk2.network.gallery.Window
import javax.inject.Inject

public class GalleryRepositoryImpl @Inject constructor(
  private val galleryService: GalleryService
) : GalleryRepository {
  override suspend fun getGallery(
    section: Section,
    sort: Sort,
    window: Window,
    page: Int
  ) {
    galleryService.getGallery(section.param, sort.param, window.param, page)
  }
}
