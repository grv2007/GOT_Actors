package com.ps.presentation.features.actordetail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.domain.extensions.onFailure
import com.ps.domain.extensions.onSuccess
import com.ps.domain.usecase.GetActorDetailUseCase
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val getActorDetailUseCase: GetActorDetailUseCase
) : ViewModel() {

    val userIntent = Channel<UiIntent>(Channel.UNLIMITED)
    private val _state = mutableStateOf<UiState<Any>>(UiState.Idle)
    val state: State<UiState<Any>> = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is UiIntent.FetchActorDetail -> fetchActorDetails(
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
            _state.value = UiState.Loading
            getActorDetailUseCase(id).onSuccess {
                _state.value = UiState.Success(it)
            }.onFailure {
                _state.value = UiState.Error(it.localizedMessage ?: "")
            }
        }
    }
}