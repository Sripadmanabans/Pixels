package com.adjectivemonk2.pixels.network.gallery.fake

import com.adjectivemonk2.pixels.network.model.gallery.Gif
import com.adjectivemonk2.pixels.network.model.gallery.Jpeg
import com.adjectivemonk2.pixels.network.model.gallery.Mp4
import com.adjectivemonk2.pixels.network.model.gallery.Png
import com.adjectivemonk2.pixels.network.model.gallery.Processing
import kotlinx.datetime.Instant

internal const val MediaTime1InMs = 1633673556L
internal const val MediaTime2InMs = 1633615656L
internal const val MediaTime3InMs = 1633773556L
internal const val MediaTime4InMs = 1633783556L

public val jpegMedia: Jpeg = Jpeg(
  id = "JPEG id",
  title = null,
  description = null,
  dateTime = Instant.fromEpochMilliseconds(MediaTime1InMs),
  animated = false,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  bandwidth = 14532L,
  vote = null,
  favorite = false,
  nsfw = null,
  section = null,
  accountUrl = null,
  accountId = null,
  isMostViral = false,
  hasSound = false,
  tags = emptyList(),
  edited = "Really?",
  inGallery = false,
  link = "JPEG link",
)

public val pngMedia: Png = Png(
  id = "PNG id",
  title = null,
  description = null,
  dateTime = Instant.fromEpochMilliseconds(MediaTime2InMs),
  animated = false,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  bandwidth = 14532L,
  vote = null,
  favorite = false,
  nsfw = null,
  section = null,
  accountUrl = null,
  accountId = null,
  isMostViral = false,
  hasSound = false,
  tags = emptyList(),
  edited = "Really?",
  inGallery = false,
  link = "PNG link",
)

public val gifMedia: Gif = Gif(
  id = "GIF id",
  title = null,
  description = null,
  dateTime = Instant.fromEpochMilliseconds(MediaTime3InMs),
  animated = false,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  bandwidth = 14532L,
  vote = null,
  favorite = false,
  nsfw = null,
  section = null,
  accountUrl = null,
  accountId = null,
  isMostViral = false,
  hasSound = false,
  tags = emptyList(),
  edited = "Really?",
  inGallery = false,
  link = "GIF link",
)

public val mp4Media: Mp4 = Mp4(
  id = "MP4 id",
  title = null,
  description = null,
  dateTime = Instant.fromEpochMilliseconds(MediaTime4InMs),
  animated = false,
  width = 100L,
  height = 100L,
  size = 100L,
  views = 10000L,
  bandwidth = 14532L,
  vote = null,
  favorite = false,
  nsfw = null,
  section = null,
  accountUrl = null,
  accountId = null,
  isMostViral = false,
  hasSound = false,
  tags = emptyList(),
  edited = "Really?",
  inGallery = false,
  link = "MP4 link",
  mp4Size = 1000L,
  mp4 = "MP4 link",
  gifv = "MP4 gifv link",
  hls = "MP4 hls link",
  processing = Processing("passed"),
)
