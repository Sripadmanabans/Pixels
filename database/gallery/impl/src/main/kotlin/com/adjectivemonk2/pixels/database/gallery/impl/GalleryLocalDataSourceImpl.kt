package com.adjectivemonk2.pixels.database.gallery.impl

import com.adjectivemonk2.pixels.database.gallery.GalleryLocalDataSource
import com.adjectivemonk2.pixels.database.gallery.PixelsDb
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.model.gallery.Gif
import com.adjectivemonk2.pixels.model.gallery.Jpeg
import com.adjectivemonk2.pixels.model.gallery.Mp4
import com.adjectivemonk2.pixels.model.gallery.Png
import com.adjectivemonk2.pixels.model.gallery.Tag
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(scope = AppScope::class)
public class GalleryLocalDataSourceImpl @Inject constructor(
  private val pixelsDb: PixelsDb,
) : GalleryLocalDataSource {
  override suspend fun insert(galleries: List<Gallery>) {
    pixelsDb.transaction {
      galleries.forEach { gallery ->
        gallery.tags.forEach { tag ->
          insertTag(tag)
          pixelsDb.galleryTagQueries.insertGalleryTag(gallery.id, tag.name)
        }
        insertMedia(gallery)
        insertGallery(gallery)
      }
    }
  }

  private fun insertGallery(gallery: Gallery) {
    pixelsDb.galleryQueries.insertGallery(
      id = gallery.id,
      title = gallery.id,
      dateTime = gallery.dateTime,
      description = gallery.description,
      accountUrl = gallery.accountUrl,
      accountId = gallery.accountId,
      views = gallery.views,
      ups = gallery.ups,
      down = gallery.downs,
      nsfw = gallery.nsfw,
      commentCount = gallery.commentCount,
      mediaCount = gallery.mediaCount,
    )
  }

  private fun insertMedia(gallery: Gallery) {
    gallery.media.forEach { media ->
      val hasSound: Boolean?
      val mp4Size: Long?
      val hls: String?
      val type: String
      when (media) {
        is Gif -> {
          hasSound = media.hasSound
          mp4Size = null
          hls = null
          type = "gif"
        }

        is Jpeg -> {
          hasSound = null
          mp4Size = null
          hls = null
          type = "jpeg"
        }

        is Mp4 -> {
          hasSound = media.hasSound
          mp4Size = media.mp4Size
          hls = media.hls
          type = "mp4"
        }

        is Png -> {
          hasSound = null
          mp4Size = null
          hls = null
          type = "png"
        }
      }
      media.tags.forEach { tag ->
        insertTag(tag)
        pixelsDb.mediaTagQueries.insertMediaTag(mediaId = media.id, tag.name)
      }
      pixelsDb.mediaQueries.insertMedia(
        id = media.id,
        title = media.title,
        description = media.description,
        width = media.width,
        height = media.height,
        size = media.size,
        views = media.views,
        favorite = media.favorite,
        nsfw = media.nsfw,
        isMostViral = media.isMostViral,
        edited = media.edited,
        link = media.link,
        hasSound = hasSound,
        mp4Size = mp4Size,
        hls = hls,
        type = type,
        galleryId = gallery.id,
      )
    }
  }

  private fun insertTag(tag: Tag) {
    pixelsDb.tagQueries.insertTag(
      name = tag.name,
      displayName = tag.displayName,
      followers = tag.followers,
      totalItems = tag.totalItems,
      isPromoted = tag.isPromoted,
    )
  }
}
