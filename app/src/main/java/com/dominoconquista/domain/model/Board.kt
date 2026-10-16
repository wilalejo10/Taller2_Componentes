package com.dominoconquista.domain.model

/** Square grid of [Territory], stored row-major. Immutable: every change returns a new board. */
data class Board(
    val territories: List<Territory>,
    val size: Int = DEFAULT_SIZE
) {
    init {
        require(territories.size == size * size) { "Board needs ${size * size} territories" }
    }

    val positions: List<Position> get() = territories.map { it.position }


    val hasTiles: Boolean get() = territories.any { it.isOccupied }

    fun contains(position: Position): Boolean =
        position.row in 0 until size && position.col in 0 until size

    operator fun get(position: Position): Territory = territories[indexOf(position)]

    fun getOrNull(position: Position): Territory? = if (contains(position)) get(position) else null

    fun with(territory: Territory): Board =
        copy(territories = territories.toMutableList().also { it[indexOf(territory.position)] = territory })

    /** Number of territories fully controlled by [playerId]. */
    fun controlledBy(playerId: Int): Int = territories.count { it.ownerId == playerId }

    fun indexOf(position: Position): Int = position.row * size + position.col

    companion object {
        const val DEFAULT_SIZE = 5


        fun empty(size: Int = DEFAULT_SIZE): Board = Board(
            territories = (0 until size * size).map { Territory(Position(it / size, it % size)) },
            size = size
        )
    }
}
