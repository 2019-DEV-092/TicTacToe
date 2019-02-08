package org.tictactoe.domain.impl

import org.tictactoe.domain.api.Board
import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.common.Factory

class TicTacToeFactory(private val boardFactory: Factory<Board>) : Factory<TicTacToe> {

    override fun create(): TicTacToe {
        val board = boardFactory.create()
        return TicTacToeImpl(board)
    }

}