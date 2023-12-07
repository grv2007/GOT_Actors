package com.ps.presentation.features.actorlist.actordetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.domain.extensions.onFailure
import com.ps.domain.extensions.onSuccess
import com.ps.domain.usecase.GetActorDetailUseCase
import com.ps.presentation.intent.MainIntent
import com.ps.presentation.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val getActorDetailUseCase: GetActorDetailUseCase
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
                   is MainIntent.FetchActorDetail -> fetchActorDetails(
                        collector.id
                    )
                    else -> {
                        // Do Nothing
                    }
                }

            }
        }
    }

    private fun fetchActorDetails(id: Int) {
        viewModelScope.launch {
            state.value = MainState.Loading
            getActorDetailUseCase(id).onSuccess {
                state.value = MainState.Success(it)
            }.onFailure {
                state.value = MainState.Error(it.localizedMessage)
            }
        }
    }
}