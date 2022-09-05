package com.adjectivemonk2.pixels.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
  primary = purple200,
  primaryContainer = purple700,
  secondary = teal200,
)

private val LightColorPalette = lightColorScheme(
  primary = purple500,
  primaryContainer = purple700,
  secondary = teal200,
)

@Composable
public fun PixelsTheme(
  isDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colors = if (isDarkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colorScheme = colors,
    typography = Typography,
    shapes = shapes,
    content = content,
  )
}
