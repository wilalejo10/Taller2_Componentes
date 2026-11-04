// Archivo: app/src/main/java/com/dominoconquista/data/model/GameDtos.kt
package com.dominoconquista.data.model

import com.dominoconquista.domain.model.*

// Data Transfer Objects (DTOs)
data class UserDto(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val score: Int = 0
)

data class PlayerDto(
    val id: String = "",
    val name: String = "",
    val score: Int = 0,
    val tiles: List<TileDto> = emptyList()
)

data class TileDto(
    val sideA: Int = 0,
    val sideB: Int = 0
)

data class GameStateDto(
    val roomId: String = "",
    val status: String = "WAITING", // WAITING, PLAYING, FINISHED
    val players: List<PlayerDto> = emptyList(),
    val currentPlayerTurnId: String = "",
    val board: BoardDto = BoardDto(),
    val winnerId: String? = null
)

data class BoardDto(
    val placedTiles: Map<String, TileDto> = emptyMap(), // Key: "x,y"
    val conqueredTerritories: Map<String, String> = emptyMap() // Key: territoryId, Value: playerId
)

// Mappers (Funciones de extensión)
fun PlayerDto.toDomain(): Player {
    return Player(
        id = id,
        name = name,
        score = score,
        tiles = tiles.map { Tile(it.sideA, it.sideB) }
    )
}

fun GameStateDto.toDomain(): GameState {
    return GameState(
        roomId = roomId,
        status = GameStatus.valueOf(status),
        players = players.map { it.toDomain() },
        currentPlayerTurnId = currentPlayerTurnId,
        // Asume que tienes constructores correspondientes en tu dominio
        board = Board(/* mapeo del tablero */),
        winnerId = winnerId
    )
}