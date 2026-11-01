package com.dominoconquista.ui.screens.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.components.ResultRow
import com.dominoconquista.ui.model.MatchResultUi
import com.dominoconquista.ui.screens.FakeScreenData
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink

@Composable
fun ResultScreen(
    result: MatchResultUi,
    onPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit
) {
    val winner = result.ranking.firstOrNull()
    val iWon = winner != null && winner.id == result.myPlayerId

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = if (iWon) "🏆 ¡Ganaste!" else "Partida terminada",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
            if (winner != null) {
                Text("Ganador: ${winner.name}", color = Ink)
            }
            Spacer(Modifier.height(8.dp))

            // One row per player, in ranking order
            result.ranking.forEachIndexed { index, player ->
                ResultRow(
                    position = index + 1,
                    result = player,
                    isMe = player.id == result.myPlayerId
                )
            }

            Spacer(Modifier.height(16.dp))
            PrimaryButton(text = "Jugar de nuevo", onClick = onPlayAgain)
            OutlinedButton(
                onClick = onBackToMenu,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Volver al menú")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    DominoConquistaTheme {
        ResultScreen(
            result = FakeScreenData.result(),
            onPlayAgain = {},
            onBackToMenu = {}
        )
    }
}