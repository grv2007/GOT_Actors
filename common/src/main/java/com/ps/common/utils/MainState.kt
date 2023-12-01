package com.ps.common.utils

sealed class MainState <out T : Any> {
    object Idle: MainState<Nothing>()
    object Loading: MainState<Nothing>()
    data class Success<out T : Any>(val output: T) : MainState<T>()
    data class Error(val error: String?): MainState<Nothing>()
}
