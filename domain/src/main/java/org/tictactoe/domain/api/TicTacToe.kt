package org.tictactoe.domain.api

/**
 * TicTacToe game API
 */
interface TicTacToe {
    /**
     * Gets the current [GameState]
     */
    var state: GameState

    /**
     * Current player plays at given position
     * if move is allowed, updates the [GameState]
     * @param row the row on the board
     * @param col the column on the board
     * @return true if move is allowed, false otherwise
     */
    fun play(row: Int, col: Int): Boolean
}