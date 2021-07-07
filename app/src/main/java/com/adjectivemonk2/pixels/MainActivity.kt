package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.network.HttpException
import com.adjectivemonk2.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.theme.PixelsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.debug
import timber.log.error
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

  @Inject internal lateinit var repository: GalleryRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PixelsTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "start") {
          composable(route = "start") { Start() }
        }
      }
    }
  }

  override fun onResume() {
    super.onResume()
    lifecycleScope.launch {
      try {
        val response = repository.getGallery()
        Timber.debug { "Api response: $response" }
      } catch (exception: HttpException) {
        Timber.error(exception) { "Api failed" }
      } catch (exception: IOException) {
        Timber.error(exception) { "Api failed" }
      }
    }
  }
}

@Composable
fun Start() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Surface(
      modifier = Modifier.align(Alignment.BottomCenter),
      color = MaterialTheme.colors.background,
    ) {
      Greeting("Android")
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PixelsTheme {
    Start()
  }
}
