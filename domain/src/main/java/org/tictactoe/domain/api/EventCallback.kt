package org.tictactoe.domain.api

import org.tictactoe.domain.api.model.Event

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