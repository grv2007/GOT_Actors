package com.ps.data.mapper

import com.ps.data.TestObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ActorDetailDomainModelMapperTest {
    private lateinit var mapper: ActorDetailDomainModelMapper

    @Before
    fun setup() {
        mapper = ActorDetailDomainModelMapper()
    }
    @Test
    fun `ActorDetailDomainModelMapper converts ActorResponse to ActorDetailModel`() {
        val data = mapper.mapToDomainModel(TestObject.actorDto)
        Assert.assertEquals(data.id, TestObject.ID)
        Assert.assertEquals(data.firstName, TestObject.FIRST_NAME)
        Assert.assertEquals(data.lastName, TestObject.LAST_NAME)
        Assert.assertEquals(data.fullName, TestObject.FULL_NAME)
        Assert.assertEquals(data.title, TestObject.TITLE)
        Assert.assertEquals(data.family, TestObject.FAMILY)
        Assert.assertEquals(data.imageUrl, TestObject.IMAGE_URL)

    }
}