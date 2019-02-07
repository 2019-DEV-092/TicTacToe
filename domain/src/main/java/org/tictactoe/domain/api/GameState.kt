package org.tictactoe.domain.api

/**
 * Represents current [TicTacToe] state
 */
sealed class GameState
data class PLAYING(val player: Player, val board: Board): GameState()
