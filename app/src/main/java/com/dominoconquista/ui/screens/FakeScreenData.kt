package com.dominoconquista.ui.screens

import com.dominoconquista.ui.model.HistoryItemUi
import com.dominoconquista.ui.model.MatchResultUi
import com.dominoconquista.ui.model.PlayerResultUi
import com.dominoconquista.ui.model.RoomPlayerUi
import com.dominoconquista.ui.model.RoomUiState

object FakeScreenData {

    fun room(code: String, maxPlayers: Int) = RoomUiState(
        code = code,
        maxPlayers = maxPlayers,
        players = listOf(
            RoomPlayerUi(id = "p1", name = "Ana", avatarIndex = 0, isHost = true),
            RoomPlayerUi(id = "p2", name = "Luis", avatarIndex = 1)
        ),
        isHost = true,
        canStart = true
    )

    fun result() = MatchResultUi(
        ranking = listOf(
            PlayerResultUi("p1", "Ana", 0, 0, 18, 8),
            PlayerResultUi("p2", "Luis", 1, 1, 12, 5),
            PlayerResultUi("p3", "Marta", 5, 2, 9, 4)
        ),
        myPlayerId = "p1"
    )

    fun history() = listOf(
        HistoryItemUi("1", true, 18, 12, "vs Luis", "28 sep 2026"),
        HistoryItemUi("2", false, 10, 16, "vs Marta", "25 sep 2026"),
        HistoryItemUi("3", true, 21, 11, "vs Luis y Marta", "22 sep 2026")
    )
}