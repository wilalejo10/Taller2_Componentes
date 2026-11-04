// Archivo: app/src/main/java/com/dominoconquista/feature/viewmodel/RoomViewModel.kt
package com.dominoconquista.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.usecase.StartGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val startGameUseCase: StartGameUseCase
) : ViewModel() {

    private val _roomState = MutableStateFlow<GameState?>(null)
    val roomState: StateFlow<GameState?> = _roomState.asStateFlow()

    fun joinAndObserveRoom(roomCode: String) {
        viewModelScope.launch {
            // Primero se une (añade su ID a la lista de players en Firestore)
            roomRepository.joinRoom(roomCode)

            // Luego observa los cambios (por ejemplo, cuando entra otro jugador)
            roomRepository.observeRoom(roomCode).collect { gameState ->
                _roomState.value = gameState

                // Si la sala está llena y el estado pasa a PLAYING, la UI debe navegar al Tablero
                if (gameState.status == GameStatus.PLAYING) {
                    // Emitir evento de navegación al GameViewModel
                }
            }
        }
    }

    fun startGame(roomCode: String) {
        viewModelScope.launch {
            // Llama al caso de uso que cambia el estado en Firestore a PLAYING y reparte las 7 fichas iniciales
            startGameUseCase.invoke(roomCode)
        }
    }
}