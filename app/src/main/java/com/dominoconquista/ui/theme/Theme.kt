package com.dominoconquista.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = Teal,
    onPrimary = Ink,
    secondary = Amber,
    background = Ivory,
    surface = SurfaceWhite,
    onBackground = Ink,
    onSurface = Ink
)

@Composable
fun DominoConquistaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}