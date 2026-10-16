package com.dominoconquista.domain.model


data class Tile(val left: Int, val right: Int) {
    init {
        require(left in 0..MAX_PIPS && right in 0..MAX_PIPS) { "Pips must be in 0..$MAX_PIPS" }
    }

    val pips: Int get() = left + right
    val isDouble: Boolean get() = left == right

    fun flipped(): Tile = Tile(right, left)
    fun hasValue(value: Int): Boolean = left == value || right == value

    fun matches(other: Tile): Boolean = this == other || this == other.flipped()

    companion object {
        const val MAX_PIPS = 6
    }
}


fun List<Tile>.withoutTile(tile: Tile): List<Tile> {
    val index = indexOfFirst { it.matches(tile) }
    return if (index < 0) this else toMutableList().also { it.removeAt(index) }
}
