package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.event.ActionResult
import com.dominoconquista.domain.event.GameEvent
import com.dominoconquista.domain.event.RejectReason
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.rules.GameRules
import com.dominoconquista.domain.rules.TurnManager

/** A player who cannot move and cannot draw loses the turn. */
class PassTurnUseCase {
    operator fun invoke(state: GameState): ActionResult {
        state.commonRejection()?.let { return ActionResult.rejected(state, it) }
        if (state.boneyard.isNotEmpty()) return ActionResult.rejected(state, RejectReason.POOL_NOT_EMPTY)
        if (GameRules.currentPlayerHasMove(state)) return ActionResult.rejected(state, RejectReason.MUST_PLAY)

        return TurnManager.endTurn(state, listOf(GameEvent.TurnPassed(state.currentPlayer.id)), passed = true)
    }
}
