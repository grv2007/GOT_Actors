package com.ps.presentation.intent

sealed class UiIntent {
    object FetchActors : UiIntent()
    class FetchActorDetail(val id: Int) : UiIntent()
}
