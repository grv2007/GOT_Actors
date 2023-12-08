package com.ps.data.repository

import com.ps.data.TestObject
import com.ps.data.dto.ActorDto
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ActorsRepositoryImplTest {
    private val dataSource = mockk<DataSource>()
    private lateinit var repository: ActorsRepositoryImpl
    private val mapper = spyk<ActorsDomainModelMapper>()

    @Before
    fun setUp() {
        repository = ActorsRepositoryImpl(dataSource,mapper)
    }

    @Test
    fun `getActors returns success`()= runBlocking {
        val mockResource = mockk<Resource.Success<List<ActorDto>>>()
        coEvery { dataSource.getActors() } returns mockResource
        coEvery { mockResource.data } returns listOf(TestObject.actorDto)
        val expectedResource = Resource.Success(TestObject.actorsModel)
        val result = repository.getActors()
        assertEquals(expectedResource, result)
        coVerify(exactly = 1) { dataSource.getActors() }
    }

    @Test
    fun `getActors returns failure`()= runBlocking {
        val mockResource = mockk<Resource.Failure>()
        coEvery { dataSource.getActors() } returns mockResource
        coEvery { mockResource.error } returns IOException()
        val result = repository.getActors()
        Assert.assertTrue(result is Resource.Failure)
        coVerify(exactly = 1) { dataSource.getActors() }
    }

}