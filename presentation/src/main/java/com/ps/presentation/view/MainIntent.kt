package com.ps.presentation.view

sealed class MainIntent {
    object FetchActors : MainIntent()
    class FetchActorDetail(val id: Int) : MainIntent()
}
