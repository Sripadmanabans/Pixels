package com.adjectivemonk2.pixels.remote.gallery.impl.converter

import com.adjectivemonk2.pixels.model.gallery.Gif
import com.adjectivemonk2.pixels.model.gallery.Jpeg
import com.adjectivemonk2.pixels.model.gallery.Media
import com.adjectivemonk2.pixels.model.gallery.Mp4
import com.adjectivemonk2.pixels.model.gallery.Png
import com.adjectivemonk2.pixels.remote.model.gallery.GifFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.JpegFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.MediaFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.Mp4FromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.PngFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.Unknown
import javax.inject.Inject

public class MediaConverter @Inject constructor(
  private val tagConverter: TagConverter,
) {
  internal fun convert(mediaFromRemote: MediaFromRemote): Media? {
    return when (mediaFromRemote) {
      is GifFromRemote -> mediaFromRemote.convert()
      is JpegFromRemote -> mediaFromRemote.convert()
      is Mp4FromRemote -> mediaFromRemote.convert()
      is PngFromRemote -> mediaFromRemote.convert()
      Unknown -> null
    }
  }

  private fun GifFromRemote.convert(): Gif {
    return Gif(
      id = id,
      title = title,
      description = description,
      width = width,
      height = height,
      size = size,
      views = views,
      favorite = favorite,
      nsfw = nsfw,
      isMostViral = isMostViral,
      tags = tags.map { tagConverter.convert(it) },
      edited = edited,
      link = link,
      hasSound = hasSound,
    )
  }

  private fun JpegFromRemote.convert(): Jpeg {
    return Jpeg(
      id = id,
      title = title,
      description = description,
      width = width,
      height = height,
      size = size,
      views = views,
      favorite = favorite,
      nsfw = nsfw,
      isMostViral = isMostViral,
      tags = tags.map { tagConverter.convert(it) },
      edited = edited,
      link = link,
    )
  }

  private fun Mp4FromRemote.convert(): Mp4 {
    return Mp4(
      id = id,
      title = title,
      description = description,
      width = width,
      height = height,
      size = size,
      views = views,
      favorite = favorite,
      nsfw = nsfw,
      isMostViral = isMostViral,
      tags = tags.map { tagConverter.convert(it) },
      edited = edited,
      link = link,
      hasSound = hasSound,
      mp4Size = mp4Size,
      hls = hls,
    )
  }

  private fun PngFromRemote.convert(): Png {
    return Png(
      id = id,
      title = title,
      description = description,
      width = width,
      height = height,
      size = size,
      views = views,
      favorite = favorite,
      nsfw = nsfw,
      isMostViral = isMostViral,
      tags = tags.map { tagConverter.convert(it) },
      edited = edited,
      link = link,
    )
  }
}
