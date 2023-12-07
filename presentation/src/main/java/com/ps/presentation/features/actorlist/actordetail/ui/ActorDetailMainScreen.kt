package com.ps.presentation.features.actorlist.actordetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.features.actorlist.actordetail.ActorDetailViewModel
import com.ps.presentation.features.actorlist.actordetail.ui.component.DetailScreen
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import com.ps.presentation.ui.component.ErrorScreen
import com.ps.presentation.ui.component.LoadingScreen


@Composable
fun ActorDetailMainScreen(id: Int) {
    val actorDetailViewModel = hiltViewModel<ActorDetailViewModel>()
    LaunchedEffect(key1 = true){
        actorDetailViewModel.userIntent.send(UiIntent.FetchActorDetail(id))
    }
    when (val state = actorDetailViewModel.state.value) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> DetailScreen(
            detail = state.output as ActorDetailModel
        )
        else -> ErrorScreen()
    }
}