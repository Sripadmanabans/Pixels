package com.adjectivemonk2.pixels

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adjectivemonk2.pixels.theme.PixelsTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class PixelActivityTest {

  @Rule @JvmField val composeTestRule = createAndroidComposeRule<PixelActivity>()

  @Test fun simpleTest() {
    composeTestRule.setContent {
      PixelsTheme {
        Greeting(name = "Android")
      }
    }
    composeTestRule.onNodeWithText("Hello Android!")
  }
}
