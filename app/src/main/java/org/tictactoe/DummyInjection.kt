package org.tictactoe

import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.common.Factory
import org.tictactoe.domain.impl.TicTacToeFactory

val ticTacToeFactory: Factory<TicTacToe> = TicTacToeFactory()