package com.dominoconquista.domain.model

data class Tile(val left: Int, val right: Int) {
    val pips: Int get() = left + right
    val isDouble: Boolean get() = left == right

    fun flipped(): Tile = Tile(right, left)
    fun hasValue(value: Int): Boolean = left == value || right == value
}
