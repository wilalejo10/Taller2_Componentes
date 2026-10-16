package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.event.ActionResult
import com.dominoconquista.domain.event.GameEvent
import com.dominoconquista.domain.event.RejectReason
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.PendingPortal
import com.dominoconquista.domain.model.Placement
import com.dominoconquista.domain.model.withoutTile
import com.dominoconquista.domain.rules.MoveValidator
import com.dominoconquista.domain.rules.PlacementResolver
import com.dominoconquista.domain.rules.TurnManager

/**
 * The current player lays a tile: validates it, conquers the covered territories, scores them and
 * ends the turn. If the tile covers a portal the turn stays open with [GameState.pendingPortal]
 * until the player relocates the tile or keeps it.
 */
class PlayTileUseCase {
    operator fun invoke(state: GameState, placement: Placement): ActionResult {
        state.commonRejection()?.let { return ActionResult.rejected(state, it) }
        val player = state.currentPlayer
        if (player.hand.none { it.matches(placement.tile) }) {
            return ActionResult.rejected(state, RejectReason.TILE_NOT_IN_HAND)
        }
        MoveValidator.validate(state.board, placement)?.let { return ActionResult.rejected(state, it) }

        val outcome = PlacementResolver.place(state.board, placement, player.id)
        val updatedPlayer = player.copy(
            hand = player.hand.withoutTile(placement.tile),
            score = player.score + outcome.scoreDelta
        )
        val players = state.players.toMutableList().also { it[state.currentPlayerIndex] = updatedPlayer }

        val events = buildList<GameEvent> {
            add(GameEvent.TilePlaced(player.id, placement))
            outcome.conquests.forEach {
                add(GameEvent.TerritoryConquered(player.id, it.position, it.type, it.controlled, it.points))
            }
        }
        val moved = state.copy(players = players, board = outcome.board)

        if (outcome.portalTriggered) {
            val pending = PendingPortal(placement, boardBefore = state.board, scoreBefore = player.score)
            return ActionResult(
                moved.copy(pendingPortal = pending),
                events + GameEvent.PortalActivated(player.id)
            )
        }
        return TurnManager.endTurn(moved, events, passed = false)
    }
}
