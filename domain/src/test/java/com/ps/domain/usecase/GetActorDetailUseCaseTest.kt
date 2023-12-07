package com.ps.domain.usecase

import com.ps.domain.model.ActorDetailModel
import com.ps.domain.repository.ActorDetailRepository
import com.ps.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetActorDetailUseCaseTest {
    private val mockActorDetailRepository = mockk<ActorDetailRepository>()

    // UseCase
    private lateinit var getActorDetailUseCase: GetActorDetailUseCase

    // Test data
    private val mockActorDetailModel = mockk<ActorDetailModel>()
    @Before
    fun setUp() {
        getActorDetailUseCase =
            GetActorDetailUseCase(mockActorDetailRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `invoke returns Resource Success`() = runBlocking {
        coEvery { mockActorDetailRepository.getActorDetail(1) } returns Resource.Success(mockActorDetailModel)
        val result = getActorDetailUseCase.invoke(1)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `invoke returns Resource Failure`() = runBlocking {
        val ioException = IOException("An error occurred")
        coEvery { mockActorDetailRepository.getActorDetail(1) } returns Resource.Failure(ioException)
        val result = getActorDetailUseCase.invoke(1)
        assertTrue(result is Resource.Failure)
    }
}