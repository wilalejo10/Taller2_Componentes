package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.rules.GameRules

class PassTurnUseCase {
    operator fun invoke(state: GameState): GameState {
        if (state.isOver) return state
        val player = state.currentPlayer
        // Can only pass when there is no move and nothing left to draw
        if (state.boneyard.isNotEmpty() || GameRules.hasAnyMove(player.hand, state.board)) return state

        val passes = state.consecutivePasses + 1
        val blocked = passes >= state.players.size
        val winnerId = if (blocked) {
            state.players.minByOrNull { GameRules.handScore(it.hand) }?.id
        } else null

        return state.copy(
            currentPlayerIndex = (state.currentPlayerIndex + 1) % state.players.size,
            consecutivePasses = passes,
            winnerId = winnerId
        )
    }
}
