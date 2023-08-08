package com.adjectivemonk2.pixels.ui.galleries.android.impl

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesRenderer
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesEvent
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesScreen
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesRendererImpl @Inject constructor() : GalleriesRenderer {
  @Composable override fun Render(
    state: GalleriesScreen,
    onEvent: (event: GalleriesEvent) -> Unit,
  ) {
    Surface {
      val error = state.error
      when {
        state.isLoading -> GalleriesLoading()
        error != null -> GalleriesError(message = error, onEvent = onEvent)
        state.galleryListItems.isEmpty() -> GalleriesEmpty()
        else -> GalleriesInfo(items = state.galleryListItems)
      }
    }
  }
}
