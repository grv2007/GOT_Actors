package com.ps.data.remote.datasource


import com.ps.data.dto.ActorDto
import com.ps.data.remote.Api
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
        val expectedResponse = mockk<Response<List<ActorDto>>>()

        coEvery { api.getActors() } returns expectedResponse

        val result = dataSource.getActors()

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getActorDetail returns expected response`() = runBlocking {
        val expectedResponse = mockk<Response<ActorDto>>()

        coEvery { api.getActorDetail(1) } returns expectedResponse

        val result = dataSource.getActorDetail(1)

        assertEquals(expectedResponse, result)
    }
}