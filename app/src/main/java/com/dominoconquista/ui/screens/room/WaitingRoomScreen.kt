package com.dominoconquista.ui.screens.room

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.dominoconquista.ui.components.PlayerSlot
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.model.RoomUiState
import com.dominoconquista.ui.screens.FakeScreenData
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.SurfaceWhite

@Composable
fun WaitingRoomScreen(
    state: RoomUiState,
    onStart: () -> Unit,
    onCancel: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Sala de espera", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Ink)

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Código de la sala", color = Ink)
                    Text(
                        text = state.code,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 8.sp,
                        color = Ink
                    )
                    Text("Comparte este código con tus amigos", color = Ink)
                }
            }

            Text(
                text = "Jugadores (${state.players.size}/${state.maxPlayers})",
                fontWeight = FontWeight.Bold,
                color = Ink
            )

            repeat(state.maxPlayers) { index ->
                PlayerSlot(player = state.players.getOrNull(index))
            }

            Spacer(Modifier.height(8.dp))

            if (state.isHost) {
                PrimaryButton(
                    text = "Iniciar partida",
                    onClick = onStart,
                    enabled = state.canStart
                )
            } else {
                Text("Esperando a que el anfitrión inicie la partida...", color = Ink)
            }

            OutlinedButton(
                onClick = onCancel,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Salir de la sala")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WaitingRoomScreenPreview() {
    DominoConquistaTheme {
        WaitingRoomScreen(
            state = FakeScreenData.room(code = "A7K3", maxPlayers = 4),
            onStart = {},
            onCancel = {}
        )
    }
}