package com.adjectivemonk2.pixels.repository.gallery.fake

import com.adjectivemonk2.pixels.model.gallery.Gif
import com.adjectivemonk2.pixels.model.gallery.Jpeg
import com.adjectivemonk2.pixels.model.gallery.Mp4
import com.adjectivemonk2.pixels.model.gallery.Png

public val jpegMedia: Jpeg = Jpeg(
  id = "JPEG id",
  title = null,
  description = null,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  favorite = false,
  nsfw = null,
  isMostViral = false,
  tags = emptyList(),
  edited = "Really?",
  link = "JPEG link",
)

public val pngMedia: Png = Png(
  id = "PNG id",
  title = null,
  description = null,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  favorite = false,
  nsfw = null,
  isMostViral = false,
  tags = emptyList(),
  edited = "Really?",
  link = "PNG link",
)

public val gifMedia: Gif = Gif(
  id = "GIF id",
  title = null,
  description = null,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  favorite = false,
  nsfw = null,
  isMostViral = false,
  tags = emptyList(),
  edited = "Really?",
  link = "GIF link",
  hasSound = false,
)

public val mp4Media: Mp4 = Mp4(
  id = "MP4 id",
  title = null,
  description = null,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  favorite = false,
  nsfw = null,
  isMostViral = false,
  tags = emptyList(),
  edited = "Really?",
  link = "MP4 link",
  hasSound = false,
  mp4Size = 1000L,
  hls = "MP4 hls link",
)
