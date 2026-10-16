package com.dominoconquista.viewmodel

import androidx.lifecycle.ViewModel
import com.dominoconquista.domain.event.ActionResult
import com.dominoconquista.domain.event.GameEvent
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Placement
import com.dominoconquista.domain.model.Position
import com.dominoconquista.domain.model.Tile
import com.dominoconquista.domain.rules.GameRules
import com.dominoconquista.domain.rules.MoveValidator
import com.dominoconquista.domain.usecase.DrawTileUseCase
import com.dominoconquista.domain.usecase.LeaveGameUseCase
import com.dominoconquista.domain.usecase.PassTurnUseCase
import com.dominoconquista.domain.usecase.PlayTileUseCase
import com.dominoconquista.domain.usecase.SkipPortalUseCase
import com.dominoconquista.domain.usecase.StartGameUseCase
import com.dominoconquista.domain.usecase.UsePortalUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Holds the match state for the UI (MVVM). The UI observes [state], [selectedTile] and
 * [highlightedPlacements] to render, and [events] for one-shot reactions (sounds, toasts).
 * All game logic lives in the domain use cases; this class only wires them to the UI flow.
 */
class GameViewModel(
    private val startGame: StartGameUseCase = StartGameUseCase(),
    private val playTile: PlayTileUseCase = PlayTileUseCase(),
    private val drawTile: DrawTileUseCase = DrawTileUseCase(),
    private val passTurn: PassTurnUseCase = PassTurnUseCase(),
    private val usePortal: UsePortalUseCase = UsePortalUseCase(),
    private val skipPortal: SkipPortalUseCase = SkipPortalUseCase(),
    private val leaveGame: LeaveGameUseCase = LeaveGameUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow<GameState?>(null)
    /** Current match, or null before the first [newGame]. */
    val state: StateFlow<GameState?> = _state.asStateFlow()

    private val _selectedTile = MutableStateFlow<Tile?>(null)
    /** Tile the current player picked from their hand (during a portal choice: the tile being moved). */
    val selectedTile: StateFlow<Tile?> = _selectedTile.asStateFlow()

    private val _highlightedPlacements = MutableStateFlow<List<Placement>>(emptyList())
    /** Every legal placement of [selectedTile]; the board highlights these cells. */
    val highlightedPlacements: StateFlow<List<Placement>> = _highlightedPlacements.asStateFlow()

    private val _events = MutableSharedFlow<GameEvent>(extraBufferCapacity = EVENT_BUFFER)
    /** One-shot notifications: conquests, draws, rejected moves, end of game... */
    val events: SharedFlow<GameEvent> = _events.asSharedFlow()

    private var lastPlayerNames: List<String> = emptyList()

    fun newGame(playerNames: List<String>) {
        lastPlayerNames = playerNames
        val game = startGame(playerNames)
        _state.value = game
        _selectedTile.value = null
        _highlightedPlacements.value = emptyList()
        _events.tryEmit(GameEvent.TurnChanged(game.currentPlayer.id, game.round))
    }

    /** Starts a fresh game with the same players. */
    fun restart() {
        if (lastPlayerNames.isNotEmpty()) newGame(lastPlayerNames)
    }

    /** The current player abandons; the game ends. */
    fun onLeave(playerId: Int) = execute { leaveGame(it, playerId) }

    fun onSelectTile(tile: Tile) {
        val game = _state.value ?: return
        if (game.isOver || game.pendingPortal != null) return
        if (game.currentPlayer.hand.none { it.matches(tile) }) return
        _selectedTile.value = tile
        refreshHighlights()
    }

    fun onClearSelection() {
        if (_state.value?.pendingPortal != null) return
        _selectedTile.value = null
        _highlightedPlacements.value = emptyList()
    }

    /** Lays the selected tile with its left half on [first] and right half on [second]. */
    fun onPlaceSelected(first: Position, second: Position) {
        val tile = _selectedTile.value ?: return
        onPlayTile(Placement(tile, first, second))
    }

    fun onPlayTile(placement: Placement) = execute { playTile(it, placement) }

    /** Moves the portal tile to a new position (see [UsePortalUseCase]). */
    fun onPortalRelocate(placement: Placement) = execute { usePortal(it, placement) }

    /** Keeps the portal tile where it is and ends the turn. */
    fun onPortalSkip() = execute { skipPortal(it) }

    fun onDrawTile() = execute { drawTile(it) }

    fun onPassTurn() = execute { passTurn(it) }

    fun canDraw(): Boolean = _state.value?.let(GameRules::canDraw) ?: false

    fun canPass(): Boolean = _state.value?.let(GameRules::canPass) ?: false

    /** Runs a use case on the current state, publishes the new state and its events. */
    private inline fun execute(action: (GameState) -> ActionResult) {
        val game = _state.value ?: return
        val result = action(game)
        _state.value = result.state
        result.events.forEach { _events.tryEmit(it) }
        syncSelection(result)
    }

    /**
     * Keeps selection and highlights consistent with the new state: an accepted action ends the
     * selection (the turn moved on), except during a portal choice where the moved tile is selected.
     */
    private fun syncSelection(result: ActionResult) {
        val pending = result.state.pendingPortal
        when {
            result.state.isOver -> _selectedTile.value = null
            pending != null -> _selectedTile.value = pending.placement.tile
            result.isAccepted -> _selectedTile.value = null
        }
        refreshHighlights()
    }

    private fun refreshHighlights() {
        val game = _state.value
        val tile = _selectedTile.value
        val pending = game?.pendingPortal
        _highlightedPlacements.value = when {
            game == null || game.isOver || tile == null -> emptyList()
            pending != null -> MoveValidator.validPlacements(tile, pending.boardBefore)
            else -> MoveValidator.validPlacements(tile, game.board)
        }
    }

    private companion object {
        const val EVENT_BUFFER = 64
    }
}
