package org.tictactoe.domain.impl

import org.tictactoe.domain.api.Board
import org.tictactoe.domain.api.CellState

/**
 * Represents the board of a [TicTacToe]
 */
class BoardImpl private constructor(
    private val cells: Array<CellState>) : Board
{
    constructor() : this(Array(size = ROWS * COLS, init = { CellState.EMPTY }))

    override operator fun get(row: Int, col: Int) = cells[row * ROWS + col]
    override operator fun set(row: Int, col: Int, value: CellState) {
        cells[row * ROWS + col] = value
    }

    /**
     * {@inheritDoc}
     */
    override fun copy() = BoardImpl(cells.copyOf())

    /**
     * {@inheritDoc}
     */
    override fun isGameOver(): CellState? {
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
     * {@inheritDoc}
     */
    override fun isFull() = cells.none { it == CellState.EMPTY }

    companion object {
        /**
         * Number of rows on a [BoardImpl]
         */
        const val ROWS = 3
        /**
         * Numbers of columns on a [BoardImpl]
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