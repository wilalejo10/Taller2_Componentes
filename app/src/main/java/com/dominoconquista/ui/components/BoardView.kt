package com.dominoconquista.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dominoconquista.ui.model.CellUi
import com.dominoconquista.ui.model.PlayerUi
import com.dominoconquista.ui.theme.playerColor

@Composable
fun BoardView(
    board: List<List<CellUi>>,
    players: List<PlayerUi>,
    onCellClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.aspectRatio(1f)) {
        board.forEach { row ->
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                row.forEach { cell ->
                    val owner = players.firstOrNull { it.id == cell.ownerId }
                    TerritoryCell(
                        cell = cell,
                        ownerColor = owner?.let { playerColor(it.colorIndex) },
                        onClick = { onCellClick(cell.row, cell.col) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    )
                }
            }
        }
    }
}
