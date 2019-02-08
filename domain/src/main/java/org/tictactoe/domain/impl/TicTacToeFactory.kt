package org.tictactoe.domain.impl

import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.common.Factory

class TicTacToeFactory: Factory<TicTacToe> {
    override fun create() = TicTacToeImpl()
}