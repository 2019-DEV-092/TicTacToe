package org.tictactoe.domain.api

/**
 * Represents the board of a [TicTacToe]
 */
class Board {

    private val cells = Array(ROWS * COLS) { CellState.EMPTY }

    operator fun get(row: Int, col: Int) = cells[row * ROWS + col]
    operator fun set(row: Int, col: Int, value: CellState) {
        cells[row * ROWS + col] = value
    }

    companion object {
        /**
         * Number of rows on a [Board]
         */
        const val ROWS = 3
        /**
         * Numbers of columns on a [Board]
         */
        const val COLS = 3
    }
}