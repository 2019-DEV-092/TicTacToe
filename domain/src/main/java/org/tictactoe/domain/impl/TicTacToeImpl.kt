package org.tictactoe.domain.impl

import org.tictactoe.domain.api.*

/**
 * Represents the Game
 */
class TicTacToeImpl : TicTacToe {

    private val board = Board()

    private var currentPlayer: Player = PlayerX

    /**
     * {@inheritDoc}
     */
    override var state: GameState = PLAYING(currentPlayer)

    /**
     * {@inheritDoc}
     */
    override fun play(row: Int, col: Int): Boolean {
        val isMoveAllowed = board[row, col] == CellState.EMPTY

        if (isMoveAllowed) {
            board[row, col] = currentPlayer.playingCell
        }

        return isMoveAllowed
    }
}