package com.dominoconquista.viewmodel

import androidx.lifecycle.ViewModel
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Side
import com.dominoconquista.domain.model.Tile
import com.dominoconquista.domain.usecase.DrawTileUseCase
import com.dominoconquista.domain.usecase.PassTurnUseCase
import com.dominoconquista.domain.usecase.PlayTileUseCase
import com.dominoconquista.domain.usecase.StartGameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val startGame: StartGameUseCase = StartGameUseCase(),
    private val playTile: PlayTileUseCase = PlayTileUseCase(),
    private val drawTile: DrawTileUseCase = DrawTileUseCase(),
    private val passTurn: PassTurnUseCase = PassTurnUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow<GameState?>(null)
    val state: StateFlow<GameState?> = _state.asStateFlow()

    fun newGame(playerNames: List<String>) {
        _state.value = startGame(playerNames)
    }

    fun onPlayTile(tile: Tile, side: Side) {
        _state.value?.let { _state.value = playTile(it, tile, side) }
    }

    fun onDrawTile() {
        _state.value?.let { _state.value = drawTile(it) }
    }

    fun onPassTurn() {
        _state.value?.let { _state.value = passTurn(it) }
    }
}
