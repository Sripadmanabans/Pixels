package com.adjectivemonk2.pixels.ui.common

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

public interface Presenter<Screen, Event> {
  @Composable public fun present(events: Flow<Event>): Screen
}
