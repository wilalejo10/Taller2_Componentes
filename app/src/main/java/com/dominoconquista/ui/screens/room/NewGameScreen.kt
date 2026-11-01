package com.dominoconquista.ui.screens.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.components.OptionSelector
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink

@Composable
fun NewGameScreen(
    onCreate: (players: Int, maxRounds: Int) -> Unit,
    onBack: () -> Unit
) {
    var players by remember { mutableStateOf(2) }
    var rounds by remember { mutableStateOf(20) }

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
            TextButton(onClick = onBack) { Text("← Volver") }
            Text("Nueva partida", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Ink)
            Text("Configura la sala antes de invitar a tus amigos", color = Ink)
            Spacer(Modifier.height(8.dp))

            Text("Número de jugadores", fontWeight = FontWeight.Bold, color = Ink)
            OptionSelector(
                options = listOf(2, 3, 4),
                selected = players,
                onSelected = { players = it }
            )

            Text("Máximo de rondas", fontWeight = FontWeight.Bold, color = Ink)
            OptionSelector(
                options = listOf(10, 20, 30),
                selected = rounds,
                onSelected = { rounds = it }
            )

            Spacer(Modifier.height(16.dp))
            PrimaryButton(text = "Crear sala", onClick = { onCreate(players, rounds) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewGameScreenPreview() {
    DominoConquistaTheme { NewGameScreen(onCreate = { _, _ -> }, onBack = {}) }
}