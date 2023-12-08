package com.ps.presentation.state

sealed class UiState <out T : Any> {
    object Idle: UiState<Nothing>()
    object Loading: UiState<Nothing>()
    data class Success<out T : Any>(val output: T) : UiState<T>()
    data class Error(val error: String): UiState<Nothing>()
}
