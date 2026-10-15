package com.dominoconquista.domain.rules

import com.dominoconquista.domain.model.Board
import com.dominoconquista.domain.model.Side
import com.dominoconquista.domain.model.Tile

object GameRules {
    const val MAX_PIPS = 6
    const val HAND_SIZE = 7

    fun createFullSet(): List<Tile> =
        (0..MAX_PIPS).flatMap { l -> (l..MAX_PIPS).map { r -> Tile(l, r) } }

    fun canPlay(tile: Tile, board: Board, side: Side): Boolean {
        val end = when (side) {
            Side.LEFT -> board.leftEnd
            Side.RIGHT -> board.rightEnd
        }
        return end == null || tile.hasValue(end)
    }

    fun hasAnyMove(hand: List<Tile>, board: Board): Boolean =
        hand.any { tile -> Side.values().any { canPlay(tile, board, it) } }

    fun placeTile(board: Board, tile: Tile, side: Side): Board {
        require(canPlay(tile, board, side)) { "Tile $tile cannot be played on $side" }
        if (board.isEmpty) return Board(listOf(tile))
        return when (side) {
            Side.LEFT -> {
                val oriented = if (tile.right == board.leftEnd) tile else tile.flipped()
                Board(listOf(oriented) + board.tiles)
            }
            Side.RIGHT -> {
                val oriented = if (tile.left == board.rightEnd) tile else tile.flipped()
                Board(board.tiles + oriented)
            }
        }
    }

    fun handScore(hand: List<Tile>): Int = hand.sumOf { it.pips }
}
