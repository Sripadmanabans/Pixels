package com.adjectivemonk2.pixels.theme

import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ThemeTest {

  @Rule @JvmField val composeRule = createAndroidComposeRule<ComponentActivity>()

  @Test fun testTheme() {
    val themeIsDark = MutableStateFlow(false)
    composeRule.setContent {
      PixelsTheme(
        isDarkTheme = themeIsDark.collectAsState().value
      ) {
        TestCompose()
      }
    }

    composeRule.onNodeWithText("Android").assertIsDisplayed()
    themeIsDark.value = true
    composeRule.onNodeWithText("Android").assertIsDisplayed()
  }

  @Suppress("TestFunctionName")
  @Composable fun TestCompose() {
    Text(text = "Android")
  }
}
