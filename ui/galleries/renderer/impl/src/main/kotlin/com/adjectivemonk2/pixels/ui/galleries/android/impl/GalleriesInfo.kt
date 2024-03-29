package com.adjectivemonk2.pixels.ui.galleries.android.impl

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleryListItem
import com.adjectivemonk2.pixels.ui.galleries.presenter.MediaItem

@Composable internal fun GalleriesInfo(items: List<GalleryListItem>) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .animateContentSize(),
  ) {
    LazyColumn {
      items(items = items, key = { it.galleryId }) { item ->
        GalleryItem(galleryListItem = item)
      }
    }
  }
}

@Composable internal fun GalleryItem(galleryListItem: GalleryListItem) {
  val painter = if (LocalInspectionMode.current) {
    painterResource(id = R.drawable.error)
  } else {
    rememberAsyncImagePainter(
      model = ImageRequest.Builder(LocalContext.current)
        .data(galleryListItem.accountImageUrl)
        .transformations(CircleCropTransformation())
        .build(),
    )
  }
  Surface {
    Row(modifier = Modifier.fillMaxWidth()) {
      Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.profile_image),
        modifier = Modifier.size(48.dp),
      )
      Text(text = galleryListItem.userId)
    }
  }
}

@Preview(name = "Gallery List Light Preview")
@Preview(name = "Gallery List Dark Preview", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GalleryListPreview(
  @PreviewParameter(GalleryItemParameterProvider::class) galleryItem: GalleryListItem,
) {
  PixelsTheme { Surface { GalleriesInfo(listOf(galleryItem)) } }
}

internal class GalleryItemParameterProvider : PreviewParameterProvider<GalleryListItem> {
  override val values: Sequence<GalleryListItem>
    get() = sequenceOf(
      GalleryListItem(
        galleryId = "123",
        userId = "Big Name to show",
        accountImageUrl = "https://www.w3schools.com/howto/img_avatar2.png",
        mediaItem = MediaItem.Image(
          id = "sample",
          url = "https://www.w3schools.com/howto/img_avatar.png",
        ),
        title = "Sample one",
        showDownArrow = false,
        diff = "20000",
        commentCount = "1234",
        views = "23456",
        showItemCount = false,
        itemCount = "10",
      ),
    )
}
