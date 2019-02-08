package org.tictactoe.domain.impl

import org.tictactoe.domain.api.*
import org.tictactoe.domain.api.model.Move
import org.tictactoe.domain.api.model.PlayerX
import org.tictactoe.domain.api.model.State

/**
 * Represents the Game
 */
class TicTacToeImpl : TicTacToe {

    override fun reset() :State = State(PlayerX, emptyList(), emptyList())

    override fun play(move: Move): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEventCallback(callback: EventCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}