package org.tictactoe.domain.impl

import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.api.model.*

/**
 * TicTacToe implementation
 */
open class TicTacToeImpl : TicTacToe {

    private lateinit var currentState: State

    override var onEvent: ((Event) -> Unit)? = null

    override fun reset() = NEW_STATE.also { currentState = it }

    override fun play(move: Move): State {

        val availableMoves = mutableListOf(*currentState.availableMoves.toTypedArray())

        if (availableMoves.remove(move)) {

            val previousMoves = listOf(*currentState.previousMoves.toTypedArray(), move)
            val winner = winner(previousMoves)

            currentState = when {

                winner != null -> {
                    onEvent?.invoke(GAMEOVER(winner))
                    State(emptyList(), previousMoves)
                }

                else -> {
                    if (availableMoves.isEmpty()) {
                        onEvent?.invoke(GAMEOVER(null))
                    }

                    val nextPlayer = nextPlayer(move.player)
                    State(availableMoves.map { Move(nextPlayer, it.position) }, previousMoves)
                }
            }
        }

        return currentState
    }

    protected open fun nextPlayer(currentPlayer: Player) =
        if (currentPlayer == PlayerX) PlayerO else PlayerX

    protected open fun winner(moves :List<Move>): Player? {
        val movesX = moves
            .filter { it.player == PlayerX }
            .map { it.position.row * ROWS + it.position.col }

        for (line in WINNING_LINES) {
            if (movesX.containsAll(line))
                return PlayerX
        }

        val movesO = moves
            .filter { it.player == PlayerO }
            .map { it.position.row * ROWS + it.position.col }

        for (line in WINNING_LINES) {
            if (movesO.containsAll(line))
                return PlayerO
        }

        return null
    }

    private companion object {
        /**
         * Number of rows
         */
        const val ROWS = 3
        /**
         * Numbers of columns
         */
        const val COLS = 3

        val PlayerX = Player("X")
        val PlayerO = Player("O")

        val NEW_STATE: State = State(
            (0 until COLS).map { col -> (0 until ROWS).map { row -> Move(PlayerX, Position(row, col)) } }.flatten(),
            emptyList()
        )

        val WINNING_LINES = arrayOf(
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