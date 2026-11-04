// Archivo: app/src/main/java/com/dominoconquista/feature/viewmodel/LobbyViewModel.kt
package com.dominoconquista.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    // Convierte el Flow de Firestore en un StateFlow para que la UI de Jetpack Compose lo observe fácilmente
    val availableRooms: StateFlow<List<GameState>> = roomRepository.getAvailableRooms()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val gameHistory: StateFlow<List<GameResult>> = roomRepository.getUserGameHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun createRoom(difficulty: String, playersCount: Int, onRoomCreated: (String) -> Unit) {
        viewModelScope.launch {
            // Genera un código tipo A7K3
            val roomCode = generateRoomCode()
            val result = roomRepository.createRoom(roomCode, difficulty, playersCount)

            result.onSuccess {
                onRoomCreated(roomCode)
            }
        }
    }

    private fun generateRoomCode(): String {
        val allowedChars = ('A'..'Z') + ('0'..'9')
        return (1..4).map { allowedChars.random() }.joinToString("")
    }
}