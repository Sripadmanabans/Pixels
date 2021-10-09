package com.adjectivemonk2.pixels.ui.galleries.android.impl

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesInfoViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Info
import com.adjectivemonk2.pixels.ui.galleries.common.GalleryListItem
import com.adjectivemonk2.pixels.ui.galleries.common.MediaItem
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesInfoViewFactoryImpl @Inject constructor() : GalleriesInfoViewFactory() {

  @Composable override fun Content(rendering: Info, viewEnvironment: ViewEnvironment) {
    Surface {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .animateContentSize(),
      ) {
        LazyColumn {
          items(items = rendering.galleryListItems) { item ->
            GalleryItem(galleryListItem = item)
          }
        }
      }
    }
  }

  @Composable internal fun GalleryItem(galleryListItem: GalleryListItem) {
    val painter = if (LocalInspectionMode.current) {
      painterResource(id = R.drawable.error)
    } else {
      rememberImagePainter(data = galleryListItem.accountImageUrl)
    }
    Image(
      painter = painter,
      contentDescription = stringResource(id = R.string.profile_image),
      modifier = Modifier.size(48.dp)
    )
  }
}

@Preview(showBackground = true)
@Composable private fun GalleriesSuccessViewFactoryPreview() {
  val factory = GalleriesInfoViewFactoryImpl()
  factory.Preview(Info(emptyList()))
}

@Preview(showBackground = true)
@Composable private fun GalleryItemPreview() {
  val factory = GalleriesInfoViewFactoryImpl()
  val galleryItem1 = GalleryListItem(
    galleryId = "123",
    userId = "asv",
    accountImageUrl = "https://www.w3schools.com/howto/img_avatar2.png",
    mediaItem = MediaItem.Image(id = "sample", "https://www.w3schools.com/howto/img_avatar.png"),
    title = "Sample one",
    showDownArrow = false,
    diff = "20000",
    commentCount = "1234",
    views = "23456",
    showItemCount = false,
    itemCount = "10",
  )
  val galleryItem2 = GalleryListItem(
    galleryId = "123",
    userId = "asv",
    accountImageUrl = "https://www.w3schools.com/howto/img_avatar2.png",
    mediaItem = MediaItem.Image(id = "sample", "https://www.w3schools.com/howto/img_avatar.png"),
    title = "Sample one",
    showDownArrow = false,
    diff = "20000",
    commentCount = "1234",
    views = "23456",
    showItemCount = false,
    itemCount = "10",
  )
  factory.Preview(Info(listOf(galleryItem1, galleryItem2)))
}
