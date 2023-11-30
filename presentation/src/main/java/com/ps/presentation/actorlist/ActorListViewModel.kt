package com.ps.gotactors.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.common.extensions.onFailure
import com.ps.common.extensions.onSuccess
import com.ps.domain.usecase.GetActorsUseCase
import com.ps.presentation.view.MainIntent
import com.ps.presentation.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorListViewModel @Inject constructor(
    private val getActorsUseCase: GetActorsUseCase
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState<Any>>(MainState.Idle)

    init {
        handleIntent()
    }
    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{ collector ->
                when(collector){
                    MainIntent.FetchActors -> fetchActors()
                    else -> {
                        // Do Nothing
                    }
                }

            }
        }
    }

    private fun fetchActors() {
        viewModelScope.launch {
            state.value = MainState.Loading
            getActorsUseCase().onSuccess {
                state.value = MainState.Success(it)
            }.onFailure {
                MainState.Error(it.localizedMessage)
            }
        }
    }
}