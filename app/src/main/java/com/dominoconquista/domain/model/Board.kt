package com.dominoconquista.domain.model

data class Board(val tiles: List<Tile> = emptyList()) {
    val isEmpty: Boolean get() = tiles.isEmpty()
    val leftEnd: Int? get() = tiles.firstOrNull()?.left
    val rightEnd: Int? get() = tiles.lastOrNull()?.right
}
