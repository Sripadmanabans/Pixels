package com.adjectivemonk2.pixels.ui.galleries.common

import com.adjectivemonk2.pixels.model.gallery.Unknown
import com.adjectivemonk2.pixels.network.gallery.fake.gifMedia
import com.adjectivemonk2.pixels.network.gallery.fake.jpegMedia
import com.adjectivemonk2.pixels.network.gallery.fake.mp4Media
import com.adjectivemonk2.pixels.network.gallery.fake.pngMedia
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class MediaConverterTest {

  @Test fun `Convert media of type gif to MediaItem of gif`() {
    val converter = MediaConverter()
    val actual = converter.toMediaItem(gifMedia)
    val expected = MediaItem.Gif(gifMedia.id, gifMedia.link)
    assertThat(actual).isEqualTo(expected)
  }

  @Test fun `Convert media of type png to MediaItem of image`() {
    val converter = MediaConverter()
    val actual = converter.toMediaItem(pngMedia)
    val expected = MediaItem.Image(pngMedia.id, pngMedia.link)
    assertThat(actual).isEqualTo(expected)
  }

  @Test fun `Convert media of type jpeg to MediaItem of image`() {
    val converter = MediaConverter()
    val actual = converter.toMediaItem(jpegMedia)
    val expected = MediaItem.Image(jpegMedia.id, jpegMedia.link)
    assertThat(actual).isEqualTo(expected)
  }

  @Test fun `Convert media of type mp4 to MediaItem of video`() {
    val converter = MediaConverter()
    val actual = converter.toMediaItem(mp4Media)
    val expected = MediaItem.Video(mp4Media.id, mp4Media.link)
    assertThat(actual).isEqualTo(expected)
  }

  @Test fun `Convert media of type unknown to null`() {
    val converter = MediaConverter()
    val actual = converter.toMediaItem(Unknown)
    assertThat(actual).isNull()
  }
}
