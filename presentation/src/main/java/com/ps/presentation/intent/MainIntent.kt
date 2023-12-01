package com.ps.presentation.intent

sealed class MainIntent {
    object FetchActors : MainIntent()
    class FetchActorDetail(val id: Int) : MainIntent()
}
