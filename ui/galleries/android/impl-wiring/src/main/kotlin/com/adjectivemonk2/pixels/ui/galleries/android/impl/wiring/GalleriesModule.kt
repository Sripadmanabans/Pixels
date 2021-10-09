package com.adjectivemonk2.pixels.ui.galleries.android.impl.wiring

import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesEmptyViewFactory
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesErrorViewFactory
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesInfoViewFactory
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesLoadingViewFactory
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.workflow1.ui.ViewRegistry
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
@ContributesTo(ActivityScope::class)
public object GalleriesModule {

  @Provides @IntoSet public fun viewFactories(
    loading: GalleriesLoadingViewFactory,
    success: GalleriesInfoViewFactory,
    empty: GalleriesEmptyViewFactory,
    error: GalleriesErrorViewFactory,
  ): ViewRegistry {
    return ViewRegistry(loading, success, empty, error)
  }
}
