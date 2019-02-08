package org.tictactoe.domain.api

import org.tictactoe.domain.api.model.Move
import org.tictactoe.domain.api.model.State

/**
 * TicTacTop interface
 */
interface TicTacToe {

    /**
     * Starts a new game
     * @return the [State] of the new game
     */
    fun reset(): State

    /**
     * Plays a [Move]
     * @param move the [Move] to play
     * @return the updated [State]
     */
    fun play(move: Move): State

    /**
     * Sets or clear the [EventCallback]
     */
    fun setEventCallback(callback: EventCallback?)

}