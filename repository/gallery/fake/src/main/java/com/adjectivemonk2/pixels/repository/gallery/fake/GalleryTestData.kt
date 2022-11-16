package com.adjectivemonk2.pixels.repository.gallery.fake

import com.adjectivemonk2.pixels.model.gallery.Gallery
import kotlinx.datetime.Instant

internal const val GalleryTime1InMs = 1633673556L
internal const val GalleryTime2InMs = 1633615656L
internal const val GalleryTime3InMs = 1633773556L

public val galleryWithMedia1: Gallery = Gallery(
  id = "galleryId1",
  title = "Gallery item 1",
  dateTime = Instant.fromEpochMilliseconds(GalleryTime1InMs),
  description = null,
  accountUrl = "Account Url",
  accountId = 1633759956L,
  views = 100L,
  ups = 50L,
  downs = 10L,
  nsfw = true,
  commentCount = 24L,
  favoriteCount = 23L,
  mediaCount = 4,
  tags = emptyList(),
  media = listOf(jpegMedia, pngMedia, gifMedia, mp4Media),
)

public val galleryWithMedia2: Gallery = Gallery(
  id = "galleryId2",
  title = "Gallery item 2",
  dateTime = Instant.fromEpochMilliseconds(GalleryTime2InMs),
  description = null,
  accountUrl = "Account Url",
  accountId = 1633757956L,
  views = 100L,
  ups = 50L,
  downs = 10L,
  nsfw = true,
  commentCount = 24L,
  favoriteCount = 23L,
  mediaCount = 2,
  tags = emptyList(),
  media = listOf(jpegMedia, mp4Media),
)

public val galleryWithOutMedia: Gallery = Gallery(
  id = "galleryId2",
  title = "Gallery item 2",
  dateTime = Instant.fromEpochMilliseconds(GalleryTime3InMs),
  description = null,
  accountUrl = "Account Url",
  accountId = 1633757956L,
  views = 100L,
  ups = 50L,
  downs = 10L,
  nsfw = true,
  commentCount = 24L,
  favoriteCount = 23L,
  mediaCount = 0,
  tags = emptyList(),
  media = emptyList(),
)
