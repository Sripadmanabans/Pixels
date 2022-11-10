package com.adjectivemonk2.pixels.network.gallery.impl.comverter

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.network.model.gallery.GalleryFromNetwork
import javax.inject.Inject

public class GalleryConverter @Inject constructor(
  private val tagConverter: TagConverter,
  private val mediaConverter: MediaConverter,
) {
  internal fun convert(galleryFromNetwork: GalleryFromNetwork): Gallery {
    return Gallery(
      id = galleryFromNetwork.id,
      title = galleryFromNetwork.title,
      dateTime = galleryFromNetwork.dateTime,
      description = galleryFromNetwork.description,
      accountUrl = galleryFromNetwork.accountUrl,
      accountId = galleryFromNetwork.accountId,
      views = galleryFromNetwork.views,
      ups = galleryFromNetwork.ups,
      downs = galleryFromNetwork.downs,
      nsfw = galleryFromNetwork.nsfw,
      commentCount = galleryFromNetwork.commentCount,
      favoriteCount = galleryFromNetwork.favoriteCount,
      mediaCount = galleryFromNetwork.imagesCount ?: 0,
      media = galleryFromNetwork.media.orEmpty().mapNotNull { mediaConverter.convert(it) },
      tags = galleryFromNetwork.tags.map { tagConverter.convert(it) },
    )
  }
}
