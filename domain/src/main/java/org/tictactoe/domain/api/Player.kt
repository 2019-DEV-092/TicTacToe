package org.tictactoe.domain.api

/**
 * Represents the Players
 */
sealed class Player
object PlayerX : Player()
object PlayerO : Player()