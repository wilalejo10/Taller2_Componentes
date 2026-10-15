package com.dominoconquista.domain.model

data class GameState(
    val players: List<Player>,
    val boneyard: List<Tile>,
    val board: Board = Board(),
    val currentPlayerIndex: Int = 0,
    val consecutivePasses: Int = 0,
    val winnerId: Int? = null
) {
    val currentPlayer: Player get() = players[currentPlayerIndex]
    val isOver: Boolean get() = winnerId != null
}
