package org.tictactoe.domain.impl

import org.tictactoe.domain.api.Board
import org.tictactoe.domain.common.Factory

class BoardFactory : Factory<Board> {

    override fun create() = BoardImpl()

}