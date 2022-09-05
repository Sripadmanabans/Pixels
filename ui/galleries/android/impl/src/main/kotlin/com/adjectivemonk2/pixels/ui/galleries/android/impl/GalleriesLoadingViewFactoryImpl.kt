package com.adjectivemonk2.pixels.ui.galleries.android.impl

import android.content.res.Configuration
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.android.GalleriesLoadingViewFactory
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Loading
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.tooling.Preview
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesLoadingViewFactoryImpl @Inject constructor() : GalleriesLoadingViewFactory() {

  @Composable override fun Content(rendering: Loading, viewEnvironment: ViewEnvironment) {
    Surface {
      Row(
        modifier = Modifier
          .fillMaxSize()
          .wrapContentSize(),
      ) {
        val transition = rememberInfiniteTransition()
        val angle by transition.animateFloat(
          initialValue = START_ANGLE,
          targetValue = END_ANGLE,
          animationSpec = infiniteRepeatable(
            animation = tween(BOX_ANIMATION_DURATION.toInt(DurationUnit.MILLISECONDS)),
            repeatMode = RepeatMode.Reverse,
          ),
        )

        Spacer(
          modifier = Modifier
            .rotate(angle)
            .background(
              color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
              shape = RoundedCornerShape(4.dp),
            )
            .size(48.dp),
        )

        Spacer(
          modifier = Modifier
            .wrapContentHeight()
            .width(12.dp),
        )

        LoadingRows(transition = transition)
      }
    }
  }

  @Composable private fun RowScope.LoadingRows(transition: InfiniteTransition) {
    Column(modifier = Modifier.align(CenterVertically)) {
      val width1 by transition.animateValue(
        initialValue = Dp.Hairline,
        targetValue = 72.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
          animation = tween(LINE_ANIMATION_DURATION.toInt(DurationUnit.MILLISECONDS)),
          repeatMode = RepeatMode.Restart,
        ),
      )
      Spacer(
        modifier = Modifier
          .background(
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            shape = RoundedCornerShape(4.dp),
          )
          .size(width1, 12.dp),
      )

      Spacer(modifier = Modifier.size(108.dp, 12.dp))

      val width2 by transition.animateValue(
        initialValue = Dp.Hairline,
        targetValue = 108.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
          animation = tween(LINE_ANIMATION_DURATION.toInt(DurationUnit.MILLISECONDS)),
          repeatMode = RepeatMode.Restart,
        ),
      )
      Spacer(
        modifier = Modifier
          .background(
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            shape = RoundedCornerShape(4.dp),
          )
          .size(width2, 12.dp),
      )
    }
  }

  private companion object {
    private const val START_ANGLE = 5F
    private const val END_ANGLE = -5F
    private val LINE_ANIMATION_DURATION = 1.seconds
    private val BOX_ANIMATION_DURATION = 500.milliseconds
  }
}

@Preview(name = "Loading dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Loading light mode")
@Composable
private fun LoadingLightPreview() {
  val factory = GalleriesLoadingViewFactoryImpl()
  PixelsTheme { factory.Preview(Loading) }
}
