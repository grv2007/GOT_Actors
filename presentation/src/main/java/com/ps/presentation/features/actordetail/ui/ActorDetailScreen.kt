package com.ps.presentation.features.actordetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.features.actordetail.ActorDetailViewModel
import com.ps.presentation.features.actordetail.ui.views.Details
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import com.ps.presentation.ui.views.CenterLoading
import com.ps.presentation.ui.views.Error


@Composable
fun ActorDetailScreen(id: Int) {
    val actorDetailViewModel = hiltViewModel<ActorDetailViewModel>()
    LaunchedEffect(key1 = true){
        actorDetailViewModel.userIntent.send(UiIntent.FetchActorDetail(id))
    }
    when (val state = actorDetailViewModel.state.value) {
        is UiState.Loading -> CenterLoading()
        is UiState.Success -> Details(
            detail = state.output as ActorDetailModel
        )
        else -> Error()
    }
}