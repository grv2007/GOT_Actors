package com.ps.domain.usecase

import com.ps.domain.model.ActorsModel
import com.ps.domain.repository.ActorsRepository
import com.ps.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetActorsUseCaseTest {
    private val mockActorsRepository = mockk<ActorsRepository>()

    // UseCase
    private lateinit var getActorsUseCase: GetActorsUseCase

    // Test data
    private val mockActorsModel = mockk<ActorsModel>()
    @Before
    fun setUp() {
        getActorsUseCase =
            GetActorsUseCase(mockActorsRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `invoke returns Resource Success`() = runBlocking {
        coEvery { mockActorsRepository.getActors() } returns Resource.Success(mockActorsModel)
        val result = getActorsUseCase.invoke()
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `invoke returns Resource Failure`() = runBlocking {
        val ioException = IOException("An error occurred")
        coEvery {  mockActorsRepository.getActors() } returns Resource.Failure(ioException)
        val result = getActorsUseCase.invoke()
        Assert.assertTrue(result is Resource.Failure)
    }
}