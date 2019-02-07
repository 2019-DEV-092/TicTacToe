package org.tictactoe.domain.api

/**
 * Represents the Players
 */
sealed class Player(val playingCell: CellState)
object PlayerX : Player(CellState.X)
object PlayerO : Player(CellState.O)