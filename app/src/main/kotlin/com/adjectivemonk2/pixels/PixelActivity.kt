package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.common.Presenter
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesRenderer
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesPresenter
import com.adjectivemonk2.pixels.ui.renderer.Renderer
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

public class PixelActivity : FragmentActivity() {

  @Inject internal lateinit var galleriesPresenter: GalleriesPresenter

  @Inject internal lateinit var galleriesRenderer: GalleriesRenderer

  override fun onCreate(savedInstanceState: Bundle?) {
    appComponent.activityComponent().injectInTo(this)
    super.onCreate(savedInstanceState)
    setContent {
      PixelsTheme {
        Render(presenter = galleriesPresenter, renderer = galleriesRenderer)
      }
    }
  }
}

@Composable internal fun <Screen, Event> Render(
  presenter: Presenter<Screen, Event>,
  renderer: Renderer<Screen, Event>,
) {
  val events = remember { MutableSharedFlow<Event>(extraBufferCapacity = 1) }
  val screen = presenter.present(events = events)
  renderer.Render(screen) { events.tryEmit(it) }
}
