package com.dominoconquista.ui.model

enum class CellType { NORMAL, BONUS, TREASURE, TRAP, FORTRESS, PORTAL }

data class TileUi(val id: Int, val left: Int, val right: Int)

data class CellUi(
    val row: Int,
    val col: Int,
    val type: CellType = CellType.NORMAL,
    val ownerId: String? = null,
    val tile: TileUi? = null,
    val isValidTarget: Boolean = false
)

data class PlayerUi(
    val id: String,
    val name: String,
    val score: Int,
    val territories: Int,
    val colorIndex: Int
)

data class GameUiState(
    val board: List<List<CellUi>>,
    val players: List<PlayerUi>,
    val hand: List<TileUi>,
    val selectedTileId: Int? = null,
    val currentTurnPlayerId: String,
    val myPlayerId: String,
    val round: Int = 1,
    val maxRounds: Int = 20,
    val poolCount: Int = 0
)
