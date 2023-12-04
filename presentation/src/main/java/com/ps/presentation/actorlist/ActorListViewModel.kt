package com.ps.presentation.actorlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.common.extensions.onFailure
import com.ps.common.extensions.onSuccess
import com.ps.common.utils.MainState
import com.ps.domain.usecase.GetActorsUseCase
import com.ps.presentation.intent.MainIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorListViewModel @Inject constructor(
    private val getActorsUseCase: GetActorsUseCase
) : ViewModel() {

   val userIntent : MutableSharedFlow<MainIntent> = MutableSharedFlow()
    var state = mutableStateOf<MainState<Any>>(MainState.Idle)
    init {
        handleIntent()
    }
    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.collect{ collector ->
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
                state.value = MainState.Error(it.localizedMessage)
            }
        }
    }
}