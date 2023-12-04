package com.ps.presentation.actordetail;

import com.ps.common.utils.MainState
import com.ps.common.utils.Resource
import com.ps.domain.usecase.GetActorDetailUseCase
import com.ps.presentation.MainDispatcherRule
import com.ps.presentation.TestObject
import com.ps.presentation.intent.MainIntent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ActorDetailViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    private val getActorDetailUseCase = mockk<GetActorDetailUseCase>()
    private lateinit var viewModel: ActorDetailViewModel

    @Before
    fun setUp() {
        viewModel = ActorDetailViewModel(getActorDetailUseCase)
    }

    @Test
    fun `FetchActorDetail emits success event`() = runTest {
        coEvery { getActorDetailUseCase(1) } returns Resource.Success(TestObject.actorDetailModel)
        Assert.assertTrue(viewModel.state.value is MainState.Idle)
        viewModel.userIntent.send(MainIntent.FetchActorDetail(1))
        delay(1000)
        Assert.assertTrue(viewModel.state.value is MainState.Success)
        Assert.assertEquals(
            (viewModel.state.value as MainState.Success).output,
            TestObject.actorDetailModel
        )
    }

    @Test
    fun `FetchActorDetail emits failure event`() = runTest {
        coEvery { getActorDetailUseCase(1) } returns Resource.Failure(IOException())
        Assert.assertTrue(viewModel.state.value is MainState.Idle)
        viewModel.userIntent.send(MainIntent.FetchActorDetail(1))
        delay(1000)
        Assert.assertTrue(viewModel.state.value is MainState.Error)
    }

}
