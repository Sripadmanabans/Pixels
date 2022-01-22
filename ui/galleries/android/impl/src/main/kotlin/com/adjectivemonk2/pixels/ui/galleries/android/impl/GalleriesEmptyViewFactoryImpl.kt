package com.adjectivemonk2.pixels.ui.galleries.android.impl

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesEmptyViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Empty
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesEmptyViewFactoryImpl @Inject constructor() : GalleriesEmptyViewFactory() {

  @Composable override fun Content(rendering: Empty, viewEnvironment: ViewEnvironment) {
    Surface {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .wrapContentSize()
          .animateContentSize()
          .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = stringResource(R.string.no_items))
      }
    }
  }
}

@Preview(name = "Empty dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Empty light mode")
@Composable private fun EmptyLightPreview() {
  val factory = GalleriesEmptyViewFactoryImpl()
  PixelsTheme { factory.Preview(Empty) }
}
