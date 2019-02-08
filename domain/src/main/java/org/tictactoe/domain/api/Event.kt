package org.tictactoe.domain.api

/**
 * Represents a game event
 */
sealed class Event

object DRAW : Event()
data class GAMEOVER internal constructor(val winner: Player) : Event()