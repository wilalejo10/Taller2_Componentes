package com.dominoconquista.domain.model

data class Player(
    val id: Int,
    val name: String,
    val hand: List<Tile> = emptyList(),
    val score: Int = 0
) {
    val handPips: Int get() = hand.sumOf { it.pips }
}
