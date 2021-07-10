package com.adjectivemonk2.lifecycle

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.savedstate.SavedStateRegistryOwner

public interface ViewModelFactoryProvider {

  public fun create(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
  ): AbstractSavedStateViewModelFactory
}
