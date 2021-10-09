package com.adjectivemonk2.pixels.ui.galleries.common

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.StableMarker

@StableMarker
public sealed class GalleriesScreen {

  @Immutable
  public object Empty : GalleriesScreen()

  @Immutable
  public data class Info(val galleryListItems: List<GalleryListItem>) : GalleriesScreen()

  @Immutable
  public object Loading : GalleriesScreen()

  @Immutable
  public data class Error(val message: String) : GalleriesScreen()
}

@Immutable
public data class GalleryListItem(
  val galleryId: String,
  val userId: String,
  val accountImageUrl: String,
  val mediaItem: MediaItem,
  val title: String,
  val showDownArrow: Boolean,
  val diff: String,
  val commentCount: String,
  val views: String,
  val showItemCount: Boolean,
  val itemCount: String,
)

@StableMarker
public sealed class MediaItem {
  public abstract val id: String
  public abstract val url: String

  @Immutable
  public data class Image(
    override val id: String,
    override val url: String,
  ) : MediaItem()

  @Immutable
  public data class Gif(
    override val id: String,
    override val url: String,
  ) : MediaItem()

  @Immutable
  public data class Video(
    override val id: String,
    override val url: String,
  ) : MediaItem()
}
