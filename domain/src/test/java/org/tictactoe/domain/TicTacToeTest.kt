package org.tictactoe.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.api.model.*

class TicTacToeTest {

    lateinit var sut: TicTacToe

    @Before
    fun before() {
        sut = ticTacToeFactory.create()
    }

    @Test
    fun `X always goes first`() {
        // act
        val state = sut.reset()

        // test
        assertEquals(PlayerX, state.currentPlayer)
    }

    @Test
    fun `Players cannot play on a played position`() {
        // arrange
        val newState = sut.reset()

        // act
        // PlayerX plays a random move
        val firstMove = newState.availableMoves.random()
        val state1 = sut.play(firstMove)
        // PlayerO try to replay PlayerX move
        val state2 = sut.play(firstMove)

        // test
        assertEquals(state1, state2)
    }

    @Test
    fun `Players alternate placing Xs and Os on the board`() {
        // arrange
        var state = sut.reset()

        // arrange
        // PlayerX plays a random move
        assertEquals(PlayerX, state.currentPlayer)
        state = sut.play(state.availableMoves.random())

        // test
        assertEquals(PlayerO, state.currentPlayer)
    }

    @Test
    fun `If a player is able to draw three Xs or three Os in a row, that player wins`() {
        // arrange
        var state = sut.reset()
        val callback = mock<(Event) -> Unit>()
        sut.onEvent = callback

        // act
        state = sut.play(state.availableMoves.find(0,0))
        // X__
        // ___
        // ___

        state = sut.play(state.availableMoves.find(2,2))
        // X__
        // ___
        // __O

        state = sut.play(state.availableMoves.find(0,1))
        // XX_
        // ___
        // __O

        state = sut.play(state.availableMoves.find(2,1))
        // XX_
        // ___
        // _OO

        state = sut.play(state.availableMoves.find(0,2))
        // XXX
        // ___
        // _OO

        // test
        assertEquals(0, state.availableMoves.size)
        verify(callback).invoke(GAMEOVER(PlayerX))
    }

    @Test
    fun `If all nine squares are filled and neither player has three in a row, the game is a draw`() {
        // arrange
        var state = sut.reset()
        val callback = mock<(Event) -> Unit>()
        sut.onEvent = callback

        // act

        state = sut.play(state.availableMoves.find(0,0))
        // X__
        // ___
        // ___

        state = sut.play(state.availableMoves.find(0,1))
        // XO_
        // ___
        // ___

        state = sut.play(state.availableMoves.find(0,2))
        // XOX
        // ___
        // ___

        state = sut.play(state.availableMoves.find(1,1))
        // XOX
        // _O_
        // ___

        state = sut.play(state.availableMoves.find(2,1))
        // XOX
        // _O_
        // _X_

        state = sut.play(state.availableMoves.find(1,2))
        // XOX
        // _OO
        // _X_

        state = sut.play(state.availableMoves.find(1,0))
        // XOX
        // XOO
        // _X_

        state = sut.play(state.availableMoves.find(2,0))
        // XOX
        // XOO
        // OX_

        state = sut.play(state.availableMoves.find(2,2))
        // XOX
        // XOO
        // OXX

        // test
        assertEquals(0, state.availableMoves.size)
        verify(callback).invoke(DRAW)
    }


    private fun List<Move>.find(row: Int, col: Int): Move =
        first { it.col == col && it.row == row }
}