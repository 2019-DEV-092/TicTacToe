package org.tictactoe.domain.impl

import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.api.model.*

/**
 * Represents the Game
 */
class TicTacToeImpl : TicTacToe {

    private lateinit var currentState: State

    override var onEvent: ((Event) -> Unit)? = null

    override fun reset() = NEW_STATE.also { currentState = it }

    override fun play(move: Move): State {
        if (currentState.availableMoves.contains(move)) {
            val previousMoves = listOf(*currentState.previousMoves.toTypedArray(), move)

            val winner = winner(previousMoves)

            currentState = if (winner != null) {
                onEvent?.invoke(GAMEOVER(winner))
                State(currentState.currentPlayer, emptyList(), previousMoves)
            } else {
                val nextPlayer = nextPlayer()
                val availableMoves = currentState.availableMoves
                    .filter { it != move }
                    .map { Move(nextPlayer, it.row, it.col) }

                if (availableMoves.isEmpty()) {
                    onEvent?.invoke(DRAW)
                }

                State(nextPlayer, availableMoves, previousMoves)
            }
        }

        return currentState
    }

    private fun nextPlayer() =
        when (currentState.currentPlayer) {
            PlayerX -> PlayerO
            PlayerO -> PlayerX
        }

    private fun winner(moves :List<Move>): Player? {
        val movesX = moves
            .filter { it.player == PlayerX }
            .map { it.row * ROWS + it.col }

        for (line in WINNING_LINES) {
            if (movesX.containsAll(line))
                return PlayerX
        }

        val movesO = moves
            .filter { it.player == PlayerO }
            .map { it.row * ROWS + it.col }

        for (line in WINNING_LINES) {
            if (movesO.containsAll(line))
                return PlayerO
        }

        return null
    }

    companion object {
        private val NEW_STATE: State = State(
            PlayerX,
            (0..2).map { col -> (0..2).map { row -> Move(PlayerX, row, col) } }.flatten(),
            emptyList()
        )

        /**
         * Number of rows on a [BoardImpl]
         */
        const val ROWS = 3
        /**
         * Numbers of columns on a [BoardImpl]
         */
        const val COLS = 3


        private val WINNING_LINES = arrayOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
    }

}