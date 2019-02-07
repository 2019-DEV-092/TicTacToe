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
    override var state: GameState = PLAYING(currentPlayer, board.copy())

    /**
     * {@inheritDoc}
     */
    override fun play(row: Int, col: Int): Boolean {
        val isMoveAllowed = board[row, col] == CellState.EMPTY

        if (isMoveAllowed) {
            board[row, col] = currentPlayer.playingCell
            updateState()
        }

        return isMoveAllowed
    }

    private fun updateState() {
        currentPlayer = nextPlayer()
        state = PLAYING(currentPlayer, board.copy())
    }

    private fun nextPlayer(): Player =
        when (currentPlayer) {
            PlayerX -> PlayerO
            PlayerO -> PlayerX
        }

}