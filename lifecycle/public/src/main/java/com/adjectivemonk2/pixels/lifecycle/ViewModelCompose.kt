package com.adjectivemonk2.pixels.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner

public val LocalViewModelFactoryProvider: ProvidableCompositionLocal<ViewModelFactoryProvider?> =
  staticCompositionLocalOf { null }

@Composable
public inline fun <reified VM : ViewModel> injectedViewModel(
  viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
  },
  viewModelFactoryProvider: ViewModelFactoryProvider? = LocalViewModelFactoryProvider.current
): VM {
  val factory = when (viewModelStoreOwner) {
    is NavBackStackEntry -> {
      viewModelFactoryProvider?.create(viewModelStoreOwner, viewModelStoreOwner.arguments)
    }
    is SavedStateRegistryOwner -> {
      viewModelFactoryProvider?.create(viewModelStoreOwner, null)
    }
    else -> null
  }
  return viewModel(viewModelStoreOwner, factory = factory)
}
