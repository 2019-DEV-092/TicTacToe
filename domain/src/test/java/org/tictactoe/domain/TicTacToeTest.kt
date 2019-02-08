package org.tictactoe.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.tictactoe.domain.api.model.PlayerX
import org.tictactoe.domain.api.TicTacToe

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
}