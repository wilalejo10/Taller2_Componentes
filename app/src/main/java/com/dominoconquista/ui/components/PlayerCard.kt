package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.model.PlayerUi
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.playerColor

@Composable
fun PlayerCard(
    player: PlayerUi,
    isCurrentTurn: Boolean,
    modifier: Modifier = Modifier
) {
    val color = playerColor(player.colorIndex)

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.35f),
        border = BorderStroke(
            if (isCurrentTurn) 3.dp else 1.dp,
            if (isCurrentTurn) Amber else color
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarView(
                avatarIndex = player.avatarIndex,
                size = 42.dp,
                backgroundColor = Color.White,
                borderColor = color
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(player.name, fontWeight = FontWeight.Bold)
                Text("Puntos: ${player.score}")
                Text("Territorios: ${player.territories}")
            }
        }
    }
}