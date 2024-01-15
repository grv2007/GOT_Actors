package com.ps.presentation.features.actorlist.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ps.domain.model.ActorsModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.features.actorlist.ui.views.ActorList
import com.ps.presentation.features.actorlist.ui.views.FetchButton
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import com.ps.presentation.ui.views.CenterLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ActorListScreen(
    onItemClick: (Int) -> Unit
) {
    val actorListViewModel = hiltViewModel<ActorListViewModel>()
    val onButtonClick: () -> Unit = {
       actorListViewModel.sendIntent(UiIntent.FetchActors)
    }
    when (val state = actorListViewModel.state.value) {
        is UiState.Idle ->  FetchButton(onButtonClick = onButtonClick)
        is UiState.Loading -> CenterLoading()
        is UiState.Success -> ActorList(
            actors = (state.output as ActorsModel).list,
            onItemClick = onItemClick
        )
        is UiState.Error -> {
            FetchButton(onButtonClick = onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
        }
    }
}
