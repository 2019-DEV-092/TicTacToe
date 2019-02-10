package org.tictactoe.domain.api.model

/**
 * Represents a state of the game
 */
data class State internal constructor(
    val currentPlayer: Player,
    val availableMoves: List<Move>,
    val previousMoves: List<PreviousMove>
)