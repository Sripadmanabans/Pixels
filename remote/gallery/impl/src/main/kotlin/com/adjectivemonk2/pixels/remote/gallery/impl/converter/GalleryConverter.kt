package com.adjectivemonk2.pixels.remote.gallery.impl.converter

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.remote.model.gallery.GalleryFromRemote
import javax.inject.Inject

public class GalleryConverter @Inject constructor(
  private val tagConverter: TagConverter,
  private val mediaConverter: MediaConverter,
) {
  internal fun convert(galleryFromRemote: GalleryFromRemote): Gallery {
    return Gallery(
      id = galleryFromRemote.id,
      title = galleryFromRemote.title,
      dateTime = galleryFromRemote.dateTime,
      description = galleryFromRemote.description,
      accountUrl = galleryFromRemote.accountUrl,
      accountId = galleryFromRemote.accountId,
      views = galleryFromRemote.views,
      ups = galleryFromRemote.ups,
      downs = galleryFromRemote.downs,
      nsfw = galleryFromRemote.nsfw,
      commentCount = galleryFromRemote.commentCount,
      favoriteCount = galleryFromRemote.favoriteCount,
      mediaCount = galleryFromRemote.imagesCount ?: 0,
      media = galleryFromRemote.media.orEmpty().mapNotNull { mediaConverter.convert(it) },
      tags = galleryFromRemote.tags.map { tagConverter.convert(it) },
    )
  }
}
