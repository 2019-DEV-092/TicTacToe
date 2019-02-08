package org.tictactoe.domain.api.model

/**
 * Represents the Players
 */
sealed class Player

object PlayerX : Player()
object PlayerO : Player()