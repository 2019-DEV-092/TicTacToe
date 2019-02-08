package org.tictactoe.domain.api

/**
 * Game event callback interface
 */
interface EventCallback {
    /**
     * Called when a game event happened
     * @param event the [Event] fired
     */
    fun onEvent(event: Event)
}