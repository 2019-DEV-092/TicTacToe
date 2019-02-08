package org.tictactoe.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.tictactoe.domain.api.model.PlayerX
import org.tictactoe.domain.api.TicTacToe
import org.tictactoe.domain.api.model.PlayerO

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
}