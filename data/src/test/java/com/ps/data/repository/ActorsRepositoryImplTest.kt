package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.TestObject
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.model.ActorResponse
import com.ps.data.remote.datasource.DataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ActorsRepositoryImplTest {
    private val dataSource = mockk<DataSource>()
    private lateinit var repository: ActorsRepositoryImpl

    @Before
    fun setUp() {
        repository = ActorsRepositoryImpl(dataSource, ActorsDomainModelMapper())
    }

    @Test
    fun `getActorDetail returns success`()= runBlocking {
        val mockResponse = mockk<Response<List<ActorResponse>>>()
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns listOf(TestObject.actorResponse)
        coEvery { dataSource.getActors() } returns mockResponse

        val expectedResource = Resource.Success(TestObject.actorsModel)

        val result = repository.getActors()

        assertEquals(expectedResource, result)

        coVerify(exactly = 1) { dataSource.getActors() }
    }


    @Test
    fun `getActorDetail returns failur`()= runBlocking {
        val mockResponse = mockk<Response<List<ActorResponse>>>()
        every { mockResponse.isSuccessful } returns false
        every { mockResponse.body() } returns listOf(TestObject.actorResponse)
        coEvery { dataSource.getActors() } returns mockResponse

        val result = repository.getActors()

        Assert.assertTrue(result is Resource.Failure)

        coVerify(exactly = 1) { dataSource.getActors() }
    }
}