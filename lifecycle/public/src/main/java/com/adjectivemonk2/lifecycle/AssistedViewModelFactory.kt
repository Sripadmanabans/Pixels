package com.adjectivemonk2.lifecycle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

public interface AssistedViewModelFactory {
  public fun create(handle: SavedStateHandle): ViewModel
}
