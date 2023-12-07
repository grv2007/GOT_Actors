package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.TestObject
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.dto.ActorDto
import com.ps.data.remote.datasource.DataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ActorDetailRepositoryImplTest {
    private val dataSource = mockk<DataSource>()
    private lateinit var repository: ActorDetailRepositoryImpl

    @Before
    fun setUp() {
        repository = ActorDetailRepositoryImpl(dataSource, ActorDetailDomainModelMapper())
    }

    @Test
    fun `getActorDetail returns success`()= runBlocking {
        val mockResponse = mockk<Response<ActorDto>>()
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns TestObject.actorDto
        coEvery { dataSource.getActorDetail(any()) } returns mockResponse

        val expectedResource = Resource.Success(TestObject.actorDetailModel)

        val result = repository.getActorDetail(1)

        assertEquals(expectedResource, result)

        coVerify(exactly = 1) { dataSource.getActorDetail(1) }
    }

    @Test
    fun `getActorDetail returns failure`()= runBlocking {
        val mockResponse = mockk<Response<ActorDto>>()
        every { mockResponse.isSuccessful } returns false
        every { mockResponse.body() } returns TestObject.actorDto
        coEvery { dataSource.getActorDetail(any()) } returns mockResponse

        val result = repository.getActorDetail(1)

        assertTrue(result is Resource.Failure)

        coVerify(exactly = 1) { dataSource.getActorDetail(1) }
    }

}