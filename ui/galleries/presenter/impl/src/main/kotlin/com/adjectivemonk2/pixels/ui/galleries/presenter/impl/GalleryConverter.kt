package com.adjectivemonk2.pixels.ui.galleries.presenter.impl

import com.adjectivemonk2.pixels.network.model.gallery.Gallery
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleryListItem
import javax.inject.Inject

public class GalleryConverter @Inject constructor(
  private val mediaConverter: MediaConverter,
  private val accountImageUrlGenerator: AccountImageUrlGenerator,
) {

  public fun toGalleryListItem(gallery: Gallery): GalleryListItem? {
    val mediaItems = gallery.media?.mapNotNull { mediaConverter.toMediaItem(it) }
    val mediaItem = mediaItems?.firstOrNull()
    return mediaItem?.let {
      GalleryListItem(
        galleryId = gallery.id,
        userId = gallery.accountUrl,
        accountImageUrl = accountImageUrlGenerator.thumbnail(gallery.accountUrl),
        mediaItem = it,
        title = gallery.title,
        showDownArrow = false,
        diff = (gallery.ups - gallery.downs).toString(),
        commentCount = gallery.commentCount.toString(),
        views = gallery.views.toString(),
        showItemCount = mediaItems.size > 1,
        itemCount = (mediaItems.size).toString(),
      )
    }
  }
}
