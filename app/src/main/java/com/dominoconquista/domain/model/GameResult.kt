package com.dominoconquista.domain.model

data class GameResult(
    val winnerName: String,
    val score: Int,
    val playedAt: Long = System.currentTimeMillis(),
    val isDraw: Boolean = false
)
