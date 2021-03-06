package com.adjectivemonk2.pixels.ui.gallery.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class GalleryScreenProviderImplTest {

  @Rule @JvmField val composeTestRule = createComposeRule()

  @Test fun myTest() {
    var state by mutableStateOf("Android")
    composeTestRule.setContent {
      Start(state = state)
    }

    composeTestRule.onNodeWithText("Hello Android!").assertIsDisplayed()
    state = "Boss"
    composeTestRule.onNodeWithText("Hello Boss!").assertIsDisplayed()
  }
}
