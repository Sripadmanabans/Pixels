package com.adjectivemonk2.lifecycle.impl

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.adjectivemonk2.lifecycle.AssistedViewModelFactory
import com.adjectivemonk2.lifecycle.ViewModelFactoryProvider
import com.adjectivemonk2.scope.ActivityScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Provider

internal typealias Factories = Provider<AssistedViewModelFactory>

@ContributesBinding(ActivityScope::class)
public class ViewModelFactoryProviderImpl @Inject constructor(
  private val assistedFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Factories>,
) : ViewModelFactoryProvider {
  override fun create(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
  ): AbstractSavedStateViewModelFactory {
    return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
      override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
      ): T {
        // Attempt to get ViewModel from assisted inject factories
        @Suppress("UNCHECKED_CAST")
        return assistedFactories[modelClass]?.let { it.get().create(handle) as T }
          ?: throw IllegalArgumentException("Unknown model class $modelClass")
      }
    }
  }
}
