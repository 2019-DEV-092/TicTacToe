package org.tictactoe.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.tictactoe.domain.api.CellState
import org.tictactoe.domain.api.PLAYING
import org.tictactoe.domain.api.PlayerX
import org.tictactoe.domain.api.TicTacToe
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
}