package com.adjectivemonk2.pixels.ui.galleries.android.impl

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesErrorViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Error
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesErrorViewFactoryImpl @Inject constructor() : GalleriesErrorViewFactory() {

  @Composable override fun Content(rendering: Error, viewEnvironment: ViewEnvironment) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .wrapContentSize()
        .animateContentSize()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = rendering.message,
        textAlign = TextAlign.Center
      )
    }
  }
}

@Preview(showBackground = true)
@Composable private fun GalleriesErrorViewFactoryPreview() {
  val factory = GalleriesErrorViewFactoryImpl()
  factory.Preview(Error("Error message"))
}
