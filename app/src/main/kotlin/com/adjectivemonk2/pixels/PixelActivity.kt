package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.common.Presenter
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesRenderer
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesPresenter
import com.adjectivemonk2.pixels.ui.renderer.Renderer
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject
import javax.inject.Provider
import kotlin.time.Duration.Companion.milliseconds

@SingleIn(ActivityScope::class)
@ContributesBinding(scope = ActivityScope::class)
public class PixelActivity @Inject constructor(
  private val galleriesPresenter: Provider<GalleriesPresenter>,
  private val galleriesRenderer: Provider<GalleriesRenderer>,
) : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PixelsTheme {
        Render(presenter = galleriesPresenter.get(), renderer = galleriesRenderer.get())
      }
    }
  }

  @Composable internal fun <Screen, Event> Render(
    presenter: Presenter<Screen, Event>,
    renderer: Renderer<Screen, Event>,
  ) {
    val events = remember { MutableSharedFlow<Event>(extraBufferCapacity = 1) }
    val screen = presenter.present(events = remember { events.debounce(DEBOUNCE) })
    renderer.Render(screen) { check(events.tryEmit(it)) { "Event $it was not consumed!!" } }
  }

  private companion object {
    private val DEBOUNCE = 300L.milliseconds
  }
}
