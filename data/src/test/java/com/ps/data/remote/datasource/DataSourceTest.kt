package com.ps.data.remote.datasource


import com.ps.data.dto.ActorDto
import com.ps.data.remote.Api
import com.ps.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DataSourceTest {

    private val api = mockk<Api>()

    private lateinit var dataSource: DataSourceImpl

    @Before
    fun setUp() {
        dataSource = DataSourceImpl(api)
    }

    @Test
    fun `getActors returns expected response`() = runBlocking {
        val mockResponse = mockk<Response<List<ActorDto>>>()
        val mockList = mockk<List<ActorDto>>()
        coEvery { api.getActors() } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns mockList
        val expectedResponse = Resource.Success(mockList)
        val result = dataSource.getActors()
        assertEquals(expectedResponse, result)
    }


    @Test
    fun `getActors returns failure response`() = runBlocking {
        val mockResponse = mockk<Response<List<ActorDto>>>()
        coEvery { api.getActors() } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns false
        val result = dataSource.getActors()
        assertTrue(result is Resource.Failure)
        coVerify(exactly = 1) { api.getActors() }
    }

    @Test
    fun `getActors returns failure response for Null`() = runBlocking {
        val mockResponse = mockk<Response<List<ActorDto>>>()
        coEvery { api.getActors() } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns null
        val result = dataSource.getActors()
        assertTrue(result is Resource.Failure)
        coVerify(exactly = 1) { api.getActors() }
    }

    @Test
    fun `getActorDetail returns expected response`() = runBlocking {
        val mockResponse = mockk<Response<ActorDto>>()
        val mockActorDto = mockk<ActorDto>()
        coEvery { api.getActorDetail(1) } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns mockActorDto
        val expectedResponse = Resource.Success(mockActorDto)
        val result = dataSource.getActorDetail(1)
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getActorDetail returns failure response`() = runBlocking {
        val mockResponse = mockk<Response<ActorDto>>()
        coEvery { api.getActorDetail(1) } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns false
        val result = dataSource.getActorDetail(1)
        assertTrue(result is Resource.Failure)
        coVerify(exactly = 1) { api.getActorDetail(1) }
    }
    @Test
    fun `getActorDetail returns failure response for Null`() = runBlocking {
        val mockResponse = mockk<Response<ActorDto>>()
        coEvery { api.getActorDetail(1) } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns null
        val result = dataSource.getActorDetail(1)
        assertTrue(result is Resource.Failure)
        coVerify(exactly = 1) { api.getActorDetail(1) }
    }

}