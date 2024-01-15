package com.ps.presentation.feature.actorlist


import com.ps.domain.usecase.GetActorsUseCase
import com.ps.domain.utils.Resource
import com.ps.presentation.MainDispatcherRule
import com.ps.presentation.TestObject.actorsModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ActorListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getActorsUseCase = mockk<GetActorsUseCase>()
    private lateinit var viewModel: ActorListViewModel

    @Before
    fun setUp() {
        viewModel = ActorListViewModel(getActorsUseCase)
    }

    @Test
    fun `FetchActors emits success event`() = runTest {
        coEvery { getActorsUseCase() } returns Resource.Success(actorsModel)
        assertTrue(viewModel.state.value is UiState.Idle)
        viewModel.userIntent.emit(UiIntent.FetchActors)
        assertTrue(viewModel.state.value is UiState.Success)
        assertEquals((viewModel.state.value as UiState.Success).output, actorsModel)
    }

    @Test
    fun `FetchActors emits failure event`() = runTest {
        coEvery { getActorsUseCase() } returns Resource.Failure(IOException())
        assertTrue(viewModel.state.value is UiState.Idle)
        viewModel.userIntent.emit(UiIntent.FetchActors)
        assertTrue(viewModel.state.value is UiState.Error)
    }

}
