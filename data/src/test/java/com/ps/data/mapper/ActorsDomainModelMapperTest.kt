package com.ps.data.mapper

import com.ps.data.TestObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class ActorsDomainModelMapperTest {
    private lateinit var mapper: ActorsDomainModelMapper

    @Before
    fun setup() {
        mapper = ActorsDomainModelMapper()
    }
    @Test
    fun `ActorsDomainModelMapper converts List of ActorResponse to ActorsModel`() {
        val data = mapper.mapToDomainModel(listOf( TestObject.actorDto)).list[0]
        Assert.assertEquals(data.id, TestObject.ID)
        Assert.assertEquals(data.fullName, TestObject.FULL_NAME)
        Assert.assertEquals(data.family, TestObject.FAMILY)
        Assert.assertEquals(data.imageUrl, TestObject.IMAGE_URL)

    }
    @Test(expected = IllegalArgumentException::class)
    fun `ActorsDomainModelMapper throws exception for null response`() {
            mapper.mapToDomainModel(null)
    }
}