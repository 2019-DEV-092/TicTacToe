package org.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tictactoe.domain.api.model.Event
import org.tictactoe.domain.api.model.Move
import org.tictactoe.domain.api.model.State

class MainViewModel: ViewModel() {

    private val ticTacToe = ticTacToeFactory.create().also {
        it.onEvent = { event -> _gameEvent.value = LiveDataEvent(event) }
    }

    private val _gameState = MutableLiveData<State>().also {
        it.value = ticTacToe.reset()
    }

    private val _gameEvent = MutableLiveData<LiveDataEvent<Event>>()

    val gameState: LiveData<State> = _gameState
    val gameEvent: LiveData<LiveDataEvent<Event>> = _gameEvent

    fun reset() {
        _gameState.value = ticTacToe.reset()
    }

    fun play(move: Move) {
        _gameState.value = ticTacToe.play(move)
    }
}