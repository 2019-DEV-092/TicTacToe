package org.tictactoe.domain.api

/**
 * Represents a [TicTacToe] state
 */
data class State internal constructor(
    val currentPlayer: Player,
    val availableMoves: List<Move>,
    val previousMoves: List<Move>
)