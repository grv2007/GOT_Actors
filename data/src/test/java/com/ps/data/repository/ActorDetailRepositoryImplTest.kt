package com.ps.data.repository


import com.ps.data.TestObject
import com.ps.data.dto.ActorDto
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.utils.Resource.Failure
import com.ps.domain.utils.Resource.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ActorDetailRepositoryImplTest {
    private val dataSource = mockk<DataSource>()
    private lateinit var repository: ActorDetailRepositoryImpl
    private val mapper = spyk<ActorDetailDomainModelMapper>()

    @Before
    fun setUp() {
        repository = ActorDetailRepositoryImpl(dataSource,mapper)
    }

    @Test
    fun `getActorDetail returns success`()= runBlocking {
        val mockResource = mockk<Success<ActorDto>>()
        coEvery { dataSource.getActorDetail(any()) } returns mockResource
        coEvery { mockResource.data } returns TestObject.actorDto
        val expectedResource = Success(TestObject.actorDetailModel)
        val result = repository.getActorDetail(1)
        assertEquals(expectedResource, result)
        coVerify(exactly = 1) { dataSource.getActorDetail(1) }
    }

    @Test
    fun `getActorDetail returns failure`()= runBlocking {
        val mockResource = mockk<Failure>()
        coEvery { dataSource.getActorDetail(any()) } returns mockResource
        coEvery { mockResource.error } returns IOException()
        val result = repository.getActorDetail(1)
        assertTrue(result is Failure)
        coVerify(exactly = 1) { dataSource.getActorDetail(1) }
    }

}