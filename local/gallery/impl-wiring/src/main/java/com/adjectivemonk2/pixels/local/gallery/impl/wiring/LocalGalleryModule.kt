package com.adjectivemonk2.pixels.local.gallery.impl.wiring

import com.adjectivemonk2.pixels.local.gallery.GalleryFromDb
import com.adjectivemonk2.pixels.local.gallery.impl.InstantColumnAdapter
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(AppScope::class)
public object LocalGalleryModule {

  @Provides
  @SingleIn(AppScope::class)
  public fun galleryDbAdapter(instantColumnAdapter: InstantColumnAdapter): GalleryFromDb.Adapter {
    return GalleryFromDb.Adapter(instantColumnAdapter)
  }
}
