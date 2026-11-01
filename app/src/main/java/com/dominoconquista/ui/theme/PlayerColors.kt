package com.dominoconquista.ui.theme

import androidx.compose.ui.graphics.Color

val playerColors = listOf(PlayerCoral, PlayerTeal, PlayerMustard, PlayerIndigo)

fun playerColor(index: Int): Color = playerColors[index % playerColors.size]
