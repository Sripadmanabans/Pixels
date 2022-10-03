package com.adjectivemonk2.pixels.ui.galleries.android.impl

import android.content.res.Configuration
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
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesEvent

@Composable internal fun GalleriesError(message: String, onEvent: (event: GalleriesEvent) -> Unit) {
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
      contentDescription = message,
    )

    Button(onClick = { onEvent(GalleriesEvent.Retry) }) {
      Text(text = stringResource(R.string.retry))
    }
  }
}

@Preview(name = "Error dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Error light mode")
@Composable
private fun ErrorLightPreview() {
  PixelsTheme { Surface { GalleriesError("Error message") { } } }
}
