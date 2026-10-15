package com.dominoconquista.domain.usecase

import com.dominoconquista.domain.model.GameState
import com.dominoconquista.domain.model.Player
import com.dominoconquista.domain.rules.GameRules
import kotlin.random.Random

class StartGameUseCase {
    operator fun invoke(
        playerNames: List<String>,
        random: Random = Random.Default
    ): GameState {
        require(playerNames.size in 2..4) { "Game needs 2 to 4 players" }
        val deck = GameRules.createFullSet().shuffled(random)
        val players = playerNames.mapIndexed { index, name ->
            val hand = deck.subList(index * GameRules.HAND_SIZE, (index + 1) * GameRules.HAND_SIZE)
            Player(id = index, name = name, hand = hand)
        }
        val boneyard = deck.drop(playerNames.size * GameRules.HAND_SIZE)
        return GameState(players = players, boneyard = boneyard)
    }
}
