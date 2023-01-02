package com.adjectivemonk2.pixels.repository.gallery.fake

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.repository.gallery.GalleryRepository
import com.adjectivemonk2.pixels.repository.gallery.Section
import com.adjectivemonk2.pixels.repository.gallery.Sort
import com.adjectivemonk2.pixels.repository.gallery.Window
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public class FakeGalleryRepository : GalleryRepository {

  private var galleriesCompletableDeferred = CompletableDeferred<List<Gallery>>()

  public fun setGalleries(galleries: List<Gallery>) {
    galleriesCompletableDeferred.complete(galleries)
  }

  public fun setException(throwable: Throwable) {
    galleriesCompletableDeferred.completeExceptionally(throwable)
  }

  public fun reset() {
    galleriesCompletableDeferred = CompletableDeferred()
  }

  override fun getGallery(
    section: Section,
    sort: Sort,
    window: Window,
    page: Int,
  ): Flow<List<Gallery>> {
    return flow {
      emit(galleriesCompletableDeferred.await())
    }
  }
}
