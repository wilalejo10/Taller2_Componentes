package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Side
import com.dominoconquista.domain.model.Tile
import com.dominoconquista.domain.rules.GameRules

class PlayTileUseCase {
    operator fun invoke(state: GameState, tile: Tile, side: Side): GameState {
        if (state.isOver) return state
        val player = state.currentPlayer
        if (tile !in player.hand || !GameRules.canPlay(tile, state.board, side)) return state

        val newHand = player.hand - tile
        val newBoard = GameRules.placeTile(state.board, tile, side)
        val newPlayers = state.players.toMutableList().apply {
            this[state.currentPlayerIndex] = player.copy(hand = newHand)
        }
        val winnerId = if (newHand.isEmpty()) player.id else null
        val nextIndex = if (winnerId != null) {
            state.currentPlayerIndex
        } else {
            (state.currentPlayerIndex + 1) % state.players.size
        }
        return state.copy(
            players = newPlayers,
            board = newBoard,
            currentPlayerIndex = nextIndex,
            consecutivePasses = 0,
            winnerId = winnerId
        )
    }
}
