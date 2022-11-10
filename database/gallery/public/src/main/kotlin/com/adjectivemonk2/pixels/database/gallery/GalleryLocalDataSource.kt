package com.adjectivemonk2.pixels.database.gallery

import com.adjectivemonk2.pixels.model.gallery.Gallery

public interface GalleryLocalDataSource {
  public suspend fun insert(galleries: List<Gallery>)
}
