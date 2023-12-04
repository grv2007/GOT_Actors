package com.ps.presentation.actorlist;

import com.product.common.utils.MainCoroutineRule
import com.product.common.utils.MainDispatcherRule
import com.ps.common.utils.MainState
import com.ps.common.utils.Resource
import com.ps.domain.model.ActorsModel
import com.ps.domain.usecase.GetActorsUseCase
import com.ps.presentation.TestObject.actorsModel
import com.ps.presentation.intent.MainIntent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ActorListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainTestCoroutineRule = MainCoroutineRule()

    private val getActorsUseCase = mockk<GetActorsUseCase>()
    private lateinit var viewModel: ActorListViewModel

    @Before
    fun setUp() {
        viewModel = ActorListViewModel(getActorsUseCase)
    }

    @Test
    fun `FetchActors emits success event`() = runTest {
        coEvery { getActorsUseCase() } returns Resource.Success(actorsModel)
        assertTrue(viewModel.state.value is MainState.Idle)
        viewModel.userIntent.emit(MainIntent.FetchActors)
        delay(1000)
        assertTrue(viewModel.state.value is MainState.Success)
        assertEquals((viewModel.state.value as MainState.Success).output, actorsModel)
    }

    @Test
    fun `FetchActors emits failure event`() = runTest {
        coEvery { getActorsUseCase() } returns Resource.Failure(IOException())
        assertTrue(viewModel.state.value is MainState.Idle)
        viewModel.userIntent.emit(MainIntent.FetchActors)
        delay(1000)
        assertTrue(viewModel.state.value is MainState.Error)
    }

}
