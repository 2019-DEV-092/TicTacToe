package org.tictactoe.domain.common

interface Factory<T> {
    fun create(): T
}