package com.adjectivemonk2.pixels.ui.galleries.android.impl

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesErrorViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Error
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesErrorViewFactoryImpl @Inject constructor() : GalleriesErrorViewFactory() {

  @Composable override fun Content(rendering: Error, viewEnvironment: ViewEnvironment) {
    Surface {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .wrapContentSize()
          .animateContentSize()
          .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Image(
          painter = painterResource(id = R.drawable.error),
          contentDescription = rendering.message,
        )

        Button(onClick = rendering.onRetryClick) {
          Text(text = stringResource(R.string.retry))
        }
      }
    }
  }
}

@Preview(name = "Error dark mode", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Error light mode")
@Composable
private fun ErrorLightPreview() {
  val factory = GalleriesErrorViewFactoryImpl()
  PixelsTheme { factory.Preview(Error("Error message")) }
}
