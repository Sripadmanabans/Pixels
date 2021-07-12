package com.adjectivemonk2.pixels.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

public val LocalViewModelFactoryProvider: ProvidableCompositionLocal<ViewModelFactoryProvider?> =
  staticCompositionLocalOf { null }

@Composable
public inline fun <reified VM : ViewModel> injectedViewModel(
  viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
  },
  viewModelFactoryProvider: ViewModelFactoryProvider? = LocalViewModelFactoryProvider.current
): VM {
  val factory = if (viewModelStoreOwner is NavBackStackEntry) {
    viewModelFactoryProvider?.create(viewModelStoreOwner, viewModelStoreOwner.arguments)
  } else {
    null
  }
  return viewModel(viewModelStoreOwner, factory = factory)
}
