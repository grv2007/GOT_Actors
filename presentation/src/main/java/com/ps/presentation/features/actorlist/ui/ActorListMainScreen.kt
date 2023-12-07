package com.ps.presentation.features.actorlist.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ps.domain.model.ActorsModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.features.actorlist.ui.component.ActorList
import com.ps.presentation.features.actorlist.ui.component.FetchButtonScreen
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import com.ps.presentation.ui.component.LoadingScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ActorListMainScreen(
    onItemClick: (Int) -> Unit
) {
    val actorListViewModel = hiltViewModel<ActorListViewModel>()
    val onButtonClick: () -> Unit = {
        CoroutineScope(Dispatchers.Default).launch {
            actorListViewModel.userIntent.emit(UiIntent.FetchActors)
        }
    }
    when (val state = actorListViewModel.state.value) {
        is UiState.Idle ->  FetchButtonScreen(onButtonClick = onButtonClick)
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> ActorList(
            actors = (state.output as ActorsModel).list,
            onItemClick = onItemClick
        )
        is UiState.Error -> {
            FetchButtonScreen(onButtonClick = onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
        }
    }
}
