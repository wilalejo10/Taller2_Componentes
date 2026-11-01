package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.model.HistoryItemUi
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.PlayerCoral
import com.dominoconquista.ui.theme.PlayerTeal

@Composable
fun HistoryCard(
    item: HistoryItemUi,
    modifier: Modifier = Modifier
) {
    val color = if (item.won) PlayerTeal else PlayerCoral

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.35f),
        border = BorderStroke(1.dp, color)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = if (item.won) "🏆 Victoria" else "😞 Derrota",
                    fontWeight = FontWeight.Bold,
                    color = Ink
                )
                Text(item.opponents, color = Ink)
                Text(item.date, color = Ink)
            }
            Text(
                text = "${item.myScore} - ${item.opponentScore}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
        }
    }
}