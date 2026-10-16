package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.model.CellType
import com.dominoconquista.ui.model.CellUi
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.CellBonus
import com.dominoconquista.ui.theme.CellFortress
import com.dominoconquista.ui.theme.CellNormal
import com.dominoconquista.ui.theme.CellPortal
import com.dominoconquista.ui.theme.CellTrap
import com.dominoconquista.ui.theme.CellTreasure

@Composable
fun TerritoryCell(
    cell: CellUi,
    ownerColor: Color?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val baseColor = when (cell.type) {
        CellType.NORMAL -> CellNormal
        CellType.BONUS -> CellBonus
        CellType.TREASURE -> CellTreasure
        CellType.TRAP -> CellTrap
        CellType.FORTRESS -> CellFortress
        CellType.PORTAL -> CellPortal
    }
    val symbol = when (cell.type) {
        CellType.NORMAL -> ""
        CellType.BONUS -> "⭐"
        CellType.TREASURE -> "💎"
        CellType.TRAP -> "💣"
        CellType.FORTRESS -> "🛡"
        CellType.PORTAL -> "🌀"
    }
    val tile = cell.tile

    Box(
        modifier = modifier
            .padding(1.5.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(ownerColor ?: baseColor)
            .then(
                if (cell.isValidTarget) {
                    Modifier.border(BorderStroke(3.dp, Amber), RoundedCornerShape(6.dp))
                } else {
                    Modifier
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (tile != null) {
            Text("${tile.left}|${tile.right}", fontSize = 11.sp)
        } else if (symbol.isNotEmpty()) {
            Text(symbol, fontSize = 14.sp)
        }
    }
}
