package com.adjectivemonk2.pixels.ui.galleries.android.impl

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesInfoViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Info
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesInfoViewFactoryImpl @Inject constructor() : GalleriesInfoViewFactory() {

  @Composable override fun Content(rendering: Info, viewEnvironment: ViewEnvironment) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .wrapContentSize()
        .animateContentSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Success ${rendering.galleries.size}")
    }
  }
}

@Preview(showBackground = true)
@Composable private fun GalleriesSuccessViewFactoryPreview() {
  val factory = GalleriesInfoViewFactoryImpl()
  factory.Preview(Info(emptyList()))
}
