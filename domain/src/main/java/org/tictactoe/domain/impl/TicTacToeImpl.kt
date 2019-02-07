package org.tictactoe.domain.impl

import org.tictactoe.domain.api.*

/**
 * Represents the Game
 */
class TicTacToeImpl(private val board: Board) : TicTacToe {

    // PlayerX always begin
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
        val winningCells = board.isGameOver()

        state = when {
            winningCells != null ->
                GAMEOVER(if (winningCells == CellState.X) PlayerX else PlayerO, board.copy())
            board.isDraw() -> DRAW(board.copy())
            else -> {
                currentPlayer = nextPlayer()
                PLAYING(currentPlayer, board.copy())
            }
        }
    }

    private fun nextPlayer(): Player =
        when (currentPlayer) {
            PlayerX -> PlayerO
            PlayerO -> PlayerX
        }
}