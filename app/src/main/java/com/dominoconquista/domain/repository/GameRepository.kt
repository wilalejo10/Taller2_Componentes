package com.dominoconquista.domain.repository

import com.dominoconquista.domain.model.GameResult

interface GameRepository {
    suspend fun saveResult(result: GameResult)
    suspend fun getResults(): List<GameResult>
}
