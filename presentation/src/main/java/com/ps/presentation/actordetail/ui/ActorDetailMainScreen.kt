package com.ps.presentation.actordetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ps.common.utils.MainState
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.actordetail.ActorDetailViewModel
import com.ps.presentation.actordetail.ui.component.DetailScreen
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