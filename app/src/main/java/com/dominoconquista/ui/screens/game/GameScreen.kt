package com.dominoconquista.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.components.BoardView
import com.dominoconquista.ui.components.GameControls
import com.dominoconquista.ui.components.PlayerCard
import com.dominoconquista.ui.components.TileView
import com.dominoconquista.ui.model.GameUiState
import com.dominoconquista.ui.theme.DominoConquistaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    state: GameUiState,
    onTileSelected: (Int) -> Unit,
    onCellClick: (Int, Int) -> Unit,
    onPlace: () -> Unit,
    onDraw: () -> Unit,
    onLeave: () -> Unit
) {
    val currentName = state.players
        .firstOrNull { it.id == state.currentTurnPlayerId }?.name ?: ""
    val isMyTurn = state.currentTurnPlayerId == state.myPlayerId

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Turno de $currentName", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Text(
                        "Ronda ${state.round}/${state.maxRounds}  ",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            state.players.chunked(2).forEach { rowPlayers ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    rowPlayers.forEach { p ->
                        PlayerCard(
                            player = p,
                            isCurrentTurn = p.id == state.currentTurnPlayerId,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowPlayers.size == 1) Spacer(Modifier.weight(1f))
                }
            }

            BoardView(
                board = state.board,
                players = state.players,
                onCellClick = onCellClick,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Tus fichas (pozo: ${state.poolCount})", fontWeight = FontWeight.Bold)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.hand) { tile ->
                    TileView(
                        left = tile.left,
                        right = tile.right,
                        selected = tile.id == state.selectedTileId,
                        onClick = { onTileSelected(tile.id) }
                    )
                }
            }

            GameControls(
                canPlace = isMyTurn && state.selectedTileId != null,
                onPlace = onPlace,
                onDraw = onDraw,
                onLeave = onLeave
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameScreenPreview() {
    DominoConquistaTheme {
        GameScreen(
            state = FakeData.gameState(),
            onTileSelected = {},
            onCellClick = { _, _ -> },
            onPlace = {},
            onDraw = {},
            onLeave = {}
        )
    }
}