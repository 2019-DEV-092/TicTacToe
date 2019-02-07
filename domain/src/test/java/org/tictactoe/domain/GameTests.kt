package org.tictactoe.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.tictactoe.domain.api.*
import org.tictactoe.domain.impl.TicTacToeImpl

class GameTests {

    lateinit var sut: TicTacToe

    @Before
    fun before() {
        sut = TicTacToeImpl()
    }

    @Test
    fun `X always goes first`() {
        val state = sut.state

        if (state is PLAYING) {
            assertEquals(PlayerX, state.player)
        } else {
            fail()
        }
    }

    @Test
    fun `Players cannot play on a played position`() {
        // act

        sut.play(1,1)

        // test

        assertFalse(sut.play(1,1))
    }

    @Test
    fun `Players alternate placing Xs and Os on the board`() {
        // act

        sut.play(0, 0)
        val step1 = sut.state
        sut.play(0,1)
        val step2 = sut.state
        sut.play(0,2)
        val step3 = sut.state

        // test
        if (step1 is PLAYING) {
            assertEquals(CellState.X, step1.board[0 ,0])
        } else {
            fail()
        }

        if (step2 is PLAYING) {
            assertEquals(CellState.O, step2.board[0 ,1])
        } else {
            fail()
        }

        if (step3 is PLAYING) {
            assertEquals(CellState.X, step3.board[0 ,2])
        } else {
            fail()
        }
    }

    @Test
    fun `If a player is able to draw three Xs or three Os in a row, that player wins`() {
        // act

        assertTrue(sut.play(0,0))
        // X__
        // ___
        // ___

        assertTrue(sut.play(2,2))
        // X__
        // ___
        // __O

        assertTrue(sut.play(0,1))
        // XX_
        // ___
        // __O

        assertTrue(sut.play(2,1))
        // XX_
        // ___
        // _OO

        assertTrue(sut.play(0,2))
        // XXX
        // ___
        // _OO

        // test

        val state = sut.state
        if (state is GAMEOVER) {
            assertEquals(PlayerX, state.winner)
        } else {
            fail()
        }
    }

    @Test
    fun `If all nine squares are filled and neither player has three in a row, the game is a draw`() {
        // act

        assertTrue(sut.play(0,0))
        // X__
        // ___
        // ___

        assertTrue(sut.play(0,1))
        // XO_
        // ___
        // ___

        assertTrue(sut.play(0,2))
        // XOX
        // ___
        // ___

        assertTrue(sut.play(1,1))
        // XOX
        // _O_
        // ___

        assertTrue(sut.play(2,1))
        // XOX
        // _O_
        // _X_

        assertTrue(sut.play(1,2))
        // XOX
        // _OO
        // _X_

        assertTrue(sut.play(1,0))
        // XOX
        // XOO
        // _X_

        assertTrue(sut.play(2,0))
        // XOX
        // XOO
        // OX_

        assertTrue(sut.play(2,2))
        // XOX
        // XOO
        // OXX

        // test

        val state = sut.state
        assertTrue(state is DRAW)
    }
}