package com.dominoconquista.ui.screens.game

import com.dominoconquista.ui.model.*

object FakeData {
    private val specials = mapOf(
        (0 to 0) to CellType.BONUS, (0 to 6) to CellType.TREASURE,
        (2 to 4) to CellType.TRAP, (3 to 3) to CellType.PORTAL,
        (4 to 2) to CellType.FORTRESS, (6 to 6) to CellType.BONUS
    )
    private val owners = mapOf(
        (1 to 1) to "p1", (1 to 2) to "p1",
        (5 to 5) to "p2", (5 to 4) to "p2",
        (3 to 0) to "p3"
    )
    private val validTargets = setOf(1 to 3, 2 to 1, 0 to 1)

    fun gameState() = GameUiState(
        board = List(7) { r ->
            List(7) { c ->
                CellUi(
                    row = r,
                    col = c,
                    type = specials[r to c] ?: CellType.NORMAL,
                    ownerId = owners[r to c],
                    isValidTarget = (r to c) in validTargets
                )
            }
        },
        players = listOf(
            PlayerUi("p1", "Ana", 12, 2, 0),
            PlayerUi("p2", "Luis", 8, 2, 1),
            PlayerUi("p3", "Marta", 3, 1, 2)
        ),
        hand = listOf(
            TileUi(1, 6, 6), TileUi(2, 3, 5), TileUi(3, 0, 4), TileUi(4, 2, 2),
            TileUi(5, 1, 6), TileUi(6, 4, 4), TileUi(7, 5, 0)
        ),
        selectedTileId = 2,
        currentTurnPlayerId = "p1",
        myPlayerId = "p1",
        round = 3,
        maxRounds = 20,
        poolCount = 8
    )
}