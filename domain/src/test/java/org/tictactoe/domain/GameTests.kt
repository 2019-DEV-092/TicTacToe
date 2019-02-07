package org.tictactoe.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
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
}