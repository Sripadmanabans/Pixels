package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adjectivemonk2.pixels.theme.PixelsTheme

class MainActivity : AppCompatActivity() {
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
