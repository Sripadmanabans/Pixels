package com.adjectivemonk2.pixels.network.gallery.impl

import com.adjectivemonk2.pixels.database.gallery.GalleryLocalDataSource
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.network.gallery.Section
import com.adjectivemonk2.pixels.network.gallery.Sort
import com.adjectivemonk2.pixels.network.gallery.Window
import com.adjectivemonk2.pixels.network.gallery.impl.comverter.GalleryConverter
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class GalleryRepositoryImpl @Inject constructor(
  private val galleryService: GalleryService,
  private val galleryConverter: GalleryConverter,
  private val galleryLocalDataSource: GalleryLocalDataSource,
) : GalleryRepository {
  override fun getGallery(
    section: Section,
    sort: Sort,
    window: Window,
    page: Int,
  ): Flow<List<Gallery>> {
    return flow {
      val galleries = galleryService.getGallery(section.param, sort.param, window.param, page)
        .map { galleryConverter.convert(it) }
      galleryLocalDataSource.insert(galleries)
      emit(galleries)
    }
  }
}
