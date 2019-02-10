package org.tictactoe.domain.api.model

/**
 * Represents a move done by a player
 */
data class PreviousMove internal constructor(
    val player: Player,
    val move: Move
)