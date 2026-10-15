package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.rules.GameRules

class DrawTileUseCase {
    operator fun invoke(state: GameState): GameState {
        if (state.isOver || state.boneyard.isEmpty()) return state
        val player = state.currentPlayer
        if (GameRules.hasAnyMove(player.hand, state.board)) return state

        val drawn = state.boneyard.first()
        val newPlayers = state.players.toMutableList().apply {
            this[state.currentPlayerIndex] = player.copy(hand = player.hand + drawn)
        }
        return state.copy(players = newPlayers, boneyard = state.boneyard.drop(1))
    }
}
