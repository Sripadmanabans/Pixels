package com.adjectivemonk2.pixels.network.gallery.impl.comverter

import com.adjectivemonk2.pixels.model.gallery.Gif
import com.adjectivemonk2.pixels.model.gallery.Jpeg
import com.adjectivemonk2.pixels.model.gallery.Media
import com.adjectivemonk2.pixels.model.gallery.Mp4
import com.adjectivemonk2.pixels.model.gallery.Png
import com.adjectivemonk2.pixels.network.model.gallery.GifFromNetwork
import com.adjectivemonk2.pixels.network.model.gallery.JpegFromNetwork
import com.adjectivemonk2.pixels.network.model.gallery.MediaFromNetwork
import com.adjectivemonk2.pixels.network.model.gallery.Mp4FromNetwork
import com.adjectivemonk2.pixels.network.model.gallery.PngFromNetwork
import com.adjectivemonk2.pixels.network.model.gallery.Unknown
import javax.inject.Inject

public class MediaConverter @Inject constructor(
  private val tagConverter: TagConverter,
) {
  internal fun convert(mediaFromNetwork: MediaFromNetwork): Media? {
    return when (mediaFromNetwork) {
      is GifFromNetwork -> mediaFromNetwork.convert()
      is JpegFromNetwork -> mediaFromNetwork.convert()
      is Mp4FromNetwork -> mediaFromNetwork.convert()
      is PngFromNetwork -> mediaFromNetwork.convert()
      Unknown -> null
    }
  }

  private fun GifFromNetwork.convert(): Gif {
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

  private fun JpegFromNetwork.convert(): Jpeg {
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

  private fun Mp4FromNetwork.convert(): Mp4 {
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

  private fun PngFromNetwork.convert(): Png {
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
