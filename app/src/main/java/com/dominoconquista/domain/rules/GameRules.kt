package com.dominoconquista.domain.rules

import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Tile

object GameRules {
    const val MAX_PIPS = Tile.MAX_PIPS
    const val HAND_SIZE = 7
    const val MIN_PLAYERS = 2
    const val MAX_PLAYERS = 4

    fun createFullSet(): List<Tile> =
        (0..MAX_PIPS).flatMap { l -> (l..MAX_PIPS).map { r -> Tile(l, r) } }

    fun currentPlayerHasMove(state: GameState): Boolean =
        MoveValidator.hasAnyMove(state.currentPlayer.hand, state.board)

    fun canDraw(state: GameState): Boolean =
        !state.isOver && state.pendingPortal == null &&
            state.boneyard.isNotEmpty() && !currentPlayerHasMove(state)


    fun canPass(state: GameState): Boolean =
        !state.isOver && state.pendingPortal == null &&
            state.boneyard.isEmpty() && !currentPlayerHasMove(state)
}
