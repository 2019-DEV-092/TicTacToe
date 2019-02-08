package org.tictactoe.domain.api

/**
 * Represents a move a player can do
 */
data class Move internal constructor(
    val player: Player,
    val col: Int, val row: Int
)