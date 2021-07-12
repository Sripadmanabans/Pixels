package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adjectivemonk2.pixels.lifecycle.LocalViewModelFactoryProvider
import com.adjectivemonk2.pixels.lifecycle.ViewModelFactoryProvider
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.gallery.GalleryScreenProvider
import javax.inject.Inject

class PixelActivity : FragmentActivity() {

  @Inject internal lateinit var viewModelFactoryProvider: ViewModelFactoryProvider
  @Inject internal lateinit var galleryScreenProvider: GalleryScreenProvider

  override fun onCreate(savedInstanceState: Bundle?) {
    appComponent.activityComponent().injectInTo(this)
    super.onCreate(savedInstanceState)
    setContent {
      PixelsTheme {
        CompositionLocalProvider(LocalViewModelFactoryProvider provides viewModelFactoryProvider) {
          // A surface container using the 'background' color from the theme
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = "start") {
            composable(route = "start") { galleryScreenProvider.Screen() }
          }
        }
      }
    }
  }
}
