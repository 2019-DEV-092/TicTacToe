package org.tictactoe.domain.api

interface Board{

    operator fun get(row: Int, col: Int): CellState

    operator fun set(row: Int, col: Int, value: CellState)

    /**
     * Duplicate this [Board]
     */
    fun copy(): Board

    /**
     * Checks if the game is over
     * @return the winning [CellState] or null if no winner
     */
    fun isGameOver(): CellState?

    /**
     * Checks if the board is full
     */
    fun isDraw(): Boolean
}