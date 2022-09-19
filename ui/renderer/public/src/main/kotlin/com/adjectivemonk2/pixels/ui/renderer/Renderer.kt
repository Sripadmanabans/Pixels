package com.adjectivemonk2.pixels.ui.renderer

import androidx.compose.runtime.Composable

public interface Renderer<Screen, Event> {
  @Composable public fun Render(state: Screen, onEvent: (event: Event) -> Unit)
}
