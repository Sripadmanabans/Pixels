package com.adjectivemonk2.pixels.ui.galleries.presenter.impl

import com.adjectivemonk2.pixels.network.gallery.fake.galleryWithMedia1
import com.adjectivemonk2.pixels.network.gallery.fake.galleryWithOutMedia
import com.adjectivemonk2.pixels.network.gallery.fake.galleryWithUnknownMedia
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleryListItem
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class GalleryConverterTest {

  @Test fun `Gallery with media`() {
    val mediaConverter = MediaConverter()
    val accountImageUrlGenerator = AccountImageUrlGenerator()
    val galleryConverter = GalleryConverter(mediaConverter, accountImageUrlGenerator)
    val gallery = galleryWithMedia1
    val actual = galleryConverter.toGalleryListItem(gallery)
    val mediaItems = gallery.media!!.mapNotNull { mediaConverter.toMediaItem(it) }
    val mediaItem = mediaItems.first()
    val expected = GalleryListItem(
      galleryId = gallery.id,
      userId = gallery.accountUrl,
      accountImageUrl = accountImageUrlGenerator.thumbnail(gallery.accountUrl),
      mediaItem = mediaItem,
      title = gallery.title,
      showDownArrow = false,
      diff = (gallery.ups - gallery.downs).toString(),
      commentCount = gallery.commentCount.toString(),
      views = gallery.views.toString(),
      showItemCount = mediaItems.size > 1,
      itemCount = (mediaItems.size).toString(),
    )
    assertThat(actual).isEqualTo(expected)
  }

  @Test fun `Gallery with out media`() {
    val mediaConverter = MediaConverter()
    val accountImageUrlGenerator = AccountImageUrlGenerator()
    val galleryConverter = GalleryConverter(mediaConverter, accountImageUrlGenerator)
    val gallery = galleryWithOutMedia
    val actual = galleryConverter.toGalleryListItem(gallery)
    assertThat(actual).isNull()
  }

  @Test fun `Gallery with unknown media`() {
    val mediaConverter = MediaConverter()
    val accountImageUrlGenerator = AccountImageUrlGenerator()
    val galleryConverter = GalleryConverter(mediaConverter, accountImageUrlGenerator)
    val gallery = galleryWithUnknownMedia
    val actual = galleryConverter.toGalleryListItem(gallery)
    assertThat(actual).isNull()
  }
}
