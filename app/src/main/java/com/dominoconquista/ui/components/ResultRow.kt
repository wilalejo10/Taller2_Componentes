package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.model.PlayerResultUi
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.playerColor

@Composable
fun ResultRow(
    position: Int,
    result: PlayerResultUi,
    isMe: Boolean,
    modifier: Modifier = Modifier
) {
    val color = playerColor(result.colorIndex)

    val medal = when (position) {
        1 -> "🥇"
        2 -> "🥈"
        3 -> "🥉"
        else -> "$position"
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.35f),
        border = BorderStroke(
            if (position == 1) 3.dp else 1.dp,
            if (position == 1) Amber else color
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(medal, fontSize = 24.sp)
            Spacer(Modifier.width(10.dp))
            AvatarView(
                avatarIndex = result.avatarIndex,
                size = 42.dp,
                backgroundColor = Color.White,
                borderColor = color
            )
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = if (isMe) "${result.name} (tú)" else result.name,
                    fontWeight = FontWeight.Bold,
                    color = Ink
                )
                Text("Territorios: ${result.territories}", color = Ink)
            }
            Text(
                text = "${result.score} pts",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
        }
    }
}