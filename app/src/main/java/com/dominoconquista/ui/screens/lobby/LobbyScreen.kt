package com.dominoconquista.ui.screens.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.components.AppTextField
import com.dominoconquista.ui.components.AvatarView
import com.dominoconquista.ui.components.MenuCard
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.PlayerTeal
import com.dominoconquista.ui.theme.SurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(
        playerName: String,
        avatarIndex: Int,
        onNewGame: () -> Unit,
onJoinWithCode: (String) -> Unit,
onHistory: () -> Unit,
onLogout: () -> Unit
)
{
var code by remember { mutableStateOf("") }

Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
    TopAppBar(
            title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                    AvatarView(
                            avatarIndex = avatarIndex,
                            size = 38.dp,
                            backgroundColor = SurfaceWhite
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("Hola, $playerName", fontWeight = FontWeight.Bold)
            }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
    )
}
    ) { padding ->
Column(
        modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
MenuCard(
        title = "Nueva partida",
        subtitle = "Crea una sala y comparte el código",
        color = PlayerTeal,
        onClick = onNewGame
)

Card(
        shape = RoundedCornerShape(16.dp),
colors = CardDefaults.cardColors(containerColor = SurfaceWhite)
            ) {
Column(
        modifier = Modifier.padding(16.dp),
verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
Text("Unirse a partida", fontWeight = FontWeight.Bold, color = Ink)
Text("Ingresa el código de la sala (4 caracteres)", color = Ink)
AppTextField(
        value = code,
        // Codes are always uppercase and at most 4 characters long
        onValueChange = { code = it.uppercase().take(4) },
label = "Código de sala"
        )
PrimaryButton(
        text = "Unirse",
        onClick = { onJoinWithCode(code) },
enabled = code.length == 4
        )
        }
        }

OutlinedButton(
        onClick = onHistory,
        shape = RoundedCornerShape(14.dp)
            ) {
Text("Historial de partidas")
            }
TextButton(
        onClick = onLogout,
        modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
Text("Cerrar sesión")
            }
                    }
                    }
                    }

@Preview(showBackground = true)
@Composable
private fun LobbyScreenPreview() {
    DominoConquistaTheme {
        LobbyScreen(
                playerName = "Jugador",
                avatarIndex = 0,
                onNewGame = {},
                onJoinWithCode = {},
                onHistory = {},
                onLogout = {}
        )
    }
}