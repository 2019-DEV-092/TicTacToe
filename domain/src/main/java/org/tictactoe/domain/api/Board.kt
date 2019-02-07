package org.tictactoe.domain.api

/**
 * Represents the board of a [TicTacToe]
 */
class Board private constructor(
    private val cells: Array<CellState>)
{
    constructor() : this(Array(size = ROWS * COLS, init = { CellState.EMPTY}))

    operator fun get(row: Int, col: Int) = cells[row * ROWS + col]
    operator fun set(row: Int, col: Int, value: CellState) {
        cells[row * ROWS + col] = value
    }

    /**
     * Duplicate this [Board]
     */
    fun copy() = Board(cells.copyOf())

    /**
     * Checks if the game is over
     * @return the winning CellState or null if no winner
     */
    fun isGameOver(): CellState? {
        WINNING_LINES.forEach { line ->
            val (a, b, c) = line

            if (cells[a] != CellState.EMPTY
                && cells[a] == cells[b]
                && cells[a] == cells[c]) {
                return cells[a]
            }
        }

        return null
    }

    /**
     * Checks if the board is full
     */
    fun isDraw() = cells.none { it == CellState.EMPTY }

    companion object {
        /**
         * Number of rows on a [Board]
         */
        const val ROWS = 3
        /**
         * Numbers of columns on a [Board]
         */
        const val COLS = 3

        private val WINNING_LINES = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
    }
}