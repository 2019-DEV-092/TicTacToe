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

    @Test
    fun `Players alternate placing Xs and Os on the board`() {
        // arrange
        var state = sut.reset()

        // arrange
        // PlayerX plays a random move
        val moveX = state.availableMoves.random()
        assertEquals(PlayerX, moveX.player)

        state = sut.play(moveX)
        // PlayerO plays a random move
        val moveO = state.availableMoves.random()
        assertEquals(PlayerO, moveO.player)
    }
}