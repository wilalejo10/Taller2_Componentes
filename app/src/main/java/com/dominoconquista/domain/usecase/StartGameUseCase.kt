package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.model.Board
import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Player
import com.dominoconquista.domain.rules.BoardFactory
import com.dominoconquista.domain.rules.GameRules
import kotlin.random.Random

/** Deals 7 tiles to each of 2-4 players, keeps the rest as the pool and builds the board. */
class StartGameUseCase {
    operator fun invoke(
        playerNames: List<String>,
        random: Random = Random.Default,
        maxRounds: Int = GameState.DEFAULT_MAX_ROUNDS,
        board: Board = BoardFactory.create(random = random)
    ): GameState {
        require(playerNames.size in GameRules.MIN_PLAYERS..GameRules.MAX_PLAYERS) {
            "Game needs ${GameRules.MIN_PLAYERS} to ${GameRules.MAX_PLAYERS} players"
        }
        require(maxRounds > 0) { "maxRounds must be positive" }
        val deck = GameRules.createFullSet().shuffled(random)
        val players = playerNames.mapIndexed { index, name ->
            val hand = deck.subList(index * GameRules.HAND_SIZE, (index + 1) * GameRules.HAND_SIZE)
            Player(id = index, name = name, hand = hand)
        }
        val boneyard = deck.drop(playerNames.size * GameRules.HAND_SIZE)
        return GameState(players = players, boneyard = boneyard, board = board, maxRounds = maxRounds)
    }
}
