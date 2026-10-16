package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.event.ActionResult
import com.dominoconquista.domain.event.GameEvent
import com.dominoconquista.domain.event.RejectReason
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.rules.GameRules

/** A player with no valid move takes one tile from the pool; the turn does not end. */
class DrawTileUseCase {
    operator fun invoke(state: GameState): ActionResult {
        state.commonRejection()?.let { return ActionResult.rejected(state, it) }
        if (state.boneyard.isEmpty()) return ActionResult.rejected(state, RejectReason.POOL_EMPTY)
        if (GameRules.currentPlayerHasMove(state)) return ActionResult.rejected(state, RejectReason.MUST_PLAY)

        val player = state.currentPlayer
        val drawn = state.boneyard.first()
        val players = state.players.toMutableList().also {
            it[state.currentPlayerIndex] = player.copy(hand = player.hand + drawn)
        }
        return ActionResult(
            state.copy(players = players, boneyard = state.boneyard.drop(1)),
            listOf(GameEvent.TileDrawn(player.id))
        )
    }
}
