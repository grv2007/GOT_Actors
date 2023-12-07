package com.ps.presentation.features.actorlist.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ps.domain.model.ActorsModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.features.actorlist.ui.component.ActorList
import com.ps.presentation.features.actorlist.ui.component.FetchButtonScreen
import com.ps.presentation.state.MainState
import com.ps.presentation.ui.component.LoadingScreen

@Composable
fun ActorListMainScreen(
    vm: ActorListViewModel,
    onButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    when (val state = vm.state.value) {
        is MainState.Idle ->  FetchButtonScreen(onButtonClick = onButtonClick)
        is MainState.Loading -> LoadingScreen()
        is MainState.Success -> ActorList(
            actors = (state.output as ActorsModel).list,
            onItemClick = onItemClick
        )
        is MainState.Error -> {
            FetchButtonScreen(onButtonClick = onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
        }
    }
}
