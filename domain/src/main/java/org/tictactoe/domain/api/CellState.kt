package org.tictactoe.domain.api

/**
 * Represents the state of a cell in the [Board]
 */
enum class CellState {
    /**
     * A cell played by [PlayerX]
     */
    X,
    /**
     * A cell played by [PlayerO]
     */
    O,
    /**
     * An empty cell
     */
    EMPTY
}