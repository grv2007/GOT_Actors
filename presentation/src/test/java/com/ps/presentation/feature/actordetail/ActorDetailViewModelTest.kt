package com.ps.presentation.feature.actordetail


import com.ps.domain.usecase.GetActorDetailUseCase
import com.ps.domain.utils.Resource
import com.ps.presentation.MainDispatcherRule
import com.ps.presentation.TestObject
import com.ps.presentation.features.actordetail.ActorDetailViewModel
import com.ps.presentation.intent.UiIntent
import com.ps.presentation.state.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ActorDetailViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getActorDetailUseCase = mockk<GetActorDetailUseCase>()
    private lateinit var viewModel: ActorDetailViewModel

    @Before
    fun setUp() {
        viewModel = ActorDetailViewModel(getActorDetailUseCase)
    }

    @Test
    fun `FetchActorDetail emits success event`() = runTest {
        coEvery { getActorDetailUseCase(1) } returns Resource.Success(TestObject.actorDetailModel)
        Assert.assertTrue(viewModel.state.value is UiState.Idle)
        viewModel.userIntent.send(UiIntent.FetchActorDetail(1))
        Assert.assertTrue(viewModel.state.value is UiState.Success)
        Assert.assertEquals(
            (viewModel.state.value as UiState.Success).output,
            TestObject.actorDetailModel
        )
    }

    @Test
    fun `FetchActorDetail emits failure event`() = runTest {
        coEvery { getActorDetailUseCase(1) } returns Resource.Failure(IOException())
        Assert.assertTrue(viewModel.state.value is UiState.Idle)
        viewModel.userIntent.send(UiIntent.FetchActorDetail(1))
        Assert.assertTrue(viewModel.state.value is UiState.Error)
    }

}
