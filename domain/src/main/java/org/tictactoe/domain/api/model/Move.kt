package org.tictactoe.domain.api.model

/**
 * Represents a [Player] move
 */
open class Move (
    open val player: Player,
    open val position: Position
)