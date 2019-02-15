package org.tictactoe.domain.api.model

/**
 * Represents a state of the game
 */
open class State (
    open val availableMoves: List<Move>,
    open val previousMoves: List<Move>
)