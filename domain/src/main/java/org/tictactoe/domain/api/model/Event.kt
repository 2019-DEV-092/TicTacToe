package org.tictactoe.domain.api.model

/**
 * Represents a game event
 */
open class Event

open class GAMEOVER constructor(open val winner: Player?) : Event()