package com.adjectivemonk2.pixels.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import de.mannodermaus.junit5.compose.createComposeExtension
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalTestApi::class)
internal class ThemeTest {

  @Suppress("JUnitMalformedDeclaration")
  @JvmField
  @RegisterExtension
  val extension = createComposeExtension()

  @Test
  @DisplayName("Testing theme change does not affect text")
  fun testTheme() {
    extension.use {
      val themeIsDark = MutableStateFlow(false)
      setContent {
        PixelsTheme(
          isDarkTheme = themeIsDark.collectAsState().value,
        ) {
          Text(text = "Android")
        }
      }

      onNodeWithText("Android").assertIsDisplayed()
      themeIsDark.value = true
      onNodeWithText("Android").assertIsDisplayed()
    }
  }
}
