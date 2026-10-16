package com.dominoconquista.domain.model

enum class GameStatus { PLAYING, FINISHED }

enum class EndReason { NO_TILES, NO_MOVES, MAX_ROUNDS, ABANDONED }

data class PendingPortal(
    val placement: Placement,
    val boardBefore: Board,
    val scoreBefore: Int
)


data class GameState(
    val players: List<Player>,
    val boneyard: List<Tile>,
    val board: Board,
    val currentPlayerIndex: Int = 0,
    val round: Int = 1,
    val maxRounds: Int = DEFAULT_MAX_ROUNDS,
    val consecutivePasses: Int = 0,
    val pendingPortal: PendingPortal? = null,
    val status: GameStatus = GameStatus.PLAYING,
    val endReason: EndReason? = null,
    val winnerId: Int? = null,
    val abandonedById: Int? = null
) {
    val currentPlayer: Player get() = players[currentPlayerIndex]
    val isOver: Boolean get() = status == GameStatus.FINISHED
    val isDraw: Boolean get() = isOver && winnerId == null

    fun player(id: Int): Player? = players.firstOrNull { it.id == id }



    fun toResult(): GameResult? {
        if (!isOver) return null
        val winner = winnerId?.let { player(it) }
        return GameResult(
            winnerName = winner?.name.orEmpty(),
            score = winner?.score ?: players.maxOf { it.score },
            isDraw = isDraw
        )
    }

    companion object {
        const val DEFAULT_MAX_ROUNDS = 12
    }
}
