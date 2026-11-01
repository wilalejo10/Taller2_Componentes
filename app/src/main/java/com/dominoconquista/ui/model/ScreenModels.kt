package com.dominoconquista.ui.model

data class RoomPlayerUi(
    val id: String,
    val name: String,
    val avatarIndex: Int,
    val isHost: Boolean = false
)

data class RoomUiState(
    val code: String,
    val maxPlayers: Int,
    val players: List<RoomPlayerUi>,
    val isHost: Boolean,
    val canStart: Boolean
)

data class PlayerResultUi(
    val id: String,
    val name: String,
    val avatarIndex: Int,
    val colorIndex: Int,
    val score: Int,
    val territories: Int
)

data class MatchResultUi(
    val ranking: List<PlayerResultUi>,
    val myPlayerId: String
)

data class HistoryItemUi(
    val id: String,
    val won: Boolean,
    val myScore: Int,
    val opponentScore: Int,
    val opponents: String,
    val date: String
)