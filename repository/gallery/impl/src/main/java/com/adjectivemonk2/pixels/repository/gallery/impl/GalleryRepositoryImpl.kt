package com.adjectivemonk2.pixels.repository.gallery.impl

import com.adjectivemonk2.pixels.local.gallery.GalleryLocalDataSource
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.remote.gallery.GalleryRemoteDataSource
import com.adjectivemonk2.pixels.repository.gallery.GalleryRepository
import com.adjectivemonk2.pixels.repository.gallery.Section
import com.adjectivemonk2.pixels.repository.gallery.Sort
import com.adjectivemonk2.pixels.repository.gallery.Window
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class GalleryRepositoryImpl @Inject constructor(
  private val galleryRemote: GalleryRemoteDataSource,
  private val galleryLocal: GalleryLocalDataSource,
) : GalleryRepository {
  override fun getGallery(
    section: Section,
    sort: Sort,
    window: Window,
    page: Int,
  ): Flow<List<Gallery>> {
    return flow {
      val galleries = galleryRemote.getGallery(section.param, sort.param, window.param, page)
      galleryLocal.insert(galleries)
      emit(galleries)
    }
  }
}
