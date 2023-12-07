package com.ps.presentation.features.actorlist.actordetail.ui

import androidx.compose.runtime.Composable

import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.features.actorlist.actordetail.ActorDetailViewModel
import com.ps.presentation.features.actorlist.actordetail.ui.component.DetailScreen
import com.ps.presentation.state.MainState
import com.ps.presentation.ui.component.ErrorScreen
import com.ps.presentation.ui.component.LoadingScreen

@Composable
fun ActorDetailMainScreen(vm: ActorDetailViewModel) {
    when (val state = vm.state.value) {
        is MainState.Loading -> LoadingScreen()
        is MainState.Success -> DetailScreen(
            detail = state.output as ActorDetailModel
        )
        else -> ErrorScreen()
    }
}