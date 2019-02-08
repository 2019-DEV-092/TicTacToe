package org.tictactoe.domain.impl

import org.tictactoe.domain.api.*
import org.tictactoe.domain.api.model.*

/**
 * Represents the Game
 */
class TicTacToeImpl : TicTacToe {

    private lateinit var currentState: State

    override var onEvent: ((Event) -> Unit)? = null

    override fun reset() = NEWSTATE.also { currentState = it }

    override fun play(move: Move): State {
        if (currentState.availableMoves.contains(move)) {
            val nextPlayer = nextPlayer()
            val previousMoves = listOf(*currentState.previousMoves.toTypedArray(), move)
            val availableMoves = currentState.availableMoves
                .filter { it != move }
                .map { Move(nextPlayer, it.col, it.row) }
            currentState = State(nextPlayer(), availableMoves, previousMoves)
        }

        return currentState
    }

    private fun nextPlayer() =
        when (currentState.currentPlayer) {
            PlayerX -> PlayerO
            PlayerO -> PlayerX
        }

    companion object {
        private val NEWSTATE: State = State(
            PlayerX,
            (0..2).map { col -> (0..2).map { row -> Move(PlayerX, col, row) } }.flatten(),
            emptyList()
        )
    }

}