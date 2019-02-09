package org.tictactoe.domain.api.model

/**
 * Represents a move a player can do
 */
data class Move internal constructor(
    val player: Player,
    val row: Int, val col: Int
)