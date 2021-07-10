package com.adjectivemonk2.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

public val LocalViewModelFactoryProvider: ProvidableCompositionLocal<ViewModelFactoryProvider> =
  staticCompositionLocalOf { error("Using before it was set..") }

@Composable
public inline fun <reified VM : ViewModel> injectedViewModel(
  viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
  }
): VM {
  val factory = if (viewModelStoreOwner is NavBackStackEntry) {
    val factoryProvider = LocalViewModelFactoryProvider.current
    factoryProvider.create(viewModelStoreOwner, viewModelStoreOwner.arguments)
  } else {
    null
  }
  return viewModel(viewModelStoreOwner, factory = factory)
}
