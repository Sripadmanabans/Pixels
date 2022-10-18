package com.adjectivemonk2.pixels.ui.galleries.presenter.impl

import com.adjectivemonk2.pixels.network.model.gallery.Gif
import com.adjectivemonk2.pixels.network.model.gallery.Jpeg
import com.adjectivemonk2.pixels.network.model.gallery.Media
import com.adjectivemonk2.pixels.network.model.gallery.Mp4
import com.adjectivemonk2.pixels.network.model.gallery.Png
import com.adjectivemonk2.pixels.network.model.gallery.Unknown
import com.adjectivemonk2.pixels.ui.galleries.presenter.MediaItem
import javax.inject.Inject

public class MediaConverter @Inject constructor() {

  public fun toMediaItem(media: Media): MediaItem? {
    return with(media) {
      when (this) {
        is Gif -> MediaItem.Gif(id, link)
        is Jpeg -> MediaItem.Image(id, link)
        is Mp4 -> MediaItem.Video(id, link)
        is Png -> MediaItem.Image(id, link)
        Unknown -> null
      }
    }
  }
}
