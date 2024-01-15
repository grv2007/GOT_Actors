package com.ps.presentation.features.actorlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.domain.extensions.onFailure
import com.ps.domain.extensions.onSuccess
import com.ps.domain.usecase.GetActorsUseCase
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorListViewModel @Inject constructor(
    private val getActorsUseCase: GetActorsUseCase
) : ViewModel() {

    val userIntent: MutableSharedFlow<UiIntent> = MutableSharedFlow()
    private val _state = mutableStateOf<UiState<Any>>(UiState.Idle)
    val state: State<UiState<Any>> = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.collect { collector ->
                when (collector) {
                    UiIntent.FetchActors -> fetchActors()
                    else -> {
                        // Do Nothing
                    }
                }

            }
        }
    }

    private fun fetchActors() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            getActorsUseCase().onSuccess {
                _state.value = UiState.Success(it)
            }.onFailure {
                _state.value = UiState.Error(it.localizedMessage ?: "")
            }
        }
    }

     fun sendIntent(intent: UiIntent) {
        viewModelScope.launch {
            userIntent.emit(intent)
        }
    }

}