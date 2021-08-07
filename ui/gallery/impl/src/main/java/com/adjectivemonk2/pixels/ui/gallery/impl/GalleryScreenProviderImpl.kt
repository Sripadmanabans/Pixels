package com.adjectivemonk2.pixels.ui.gallery.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adjectivemonk2.pixels.lifecycle.injectedViewModel
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.gallery.GalleryScreenProvider
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(ActivityScope::class)
public class GalleryScreenProviderImpl @Inject constructor() : GalleryScreenProvider {

  @Composable
  override fun Screen() {
    val viewModel = injectedViewModel<GalleryViewModel>()
    Start(viewModel.state)
  }
}

@Composable
internal fun Start(state: State<String>) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Surface(
      modifier = Modifier.align(Alignment.Center),
      color = MaterialTheme.colors.background,
    ) {
      Greeting(state)
    }
  }
}

@Composable
internal fun Greeting(name: State<String>) {
  Text(text = "Hello ${name.value}!")
}

@Preview(showBackground = true)
@Composable
internal fun DefaultPreview() {
  PixelsTheme {
    Start(remember { mutableStateOf("Android") })
  }
}
