package com.ps.data.remote.datasource

import com.ps.data.dto.ActorDto
import com.ps.domain.utils.Resource

interface DataSource {
    suspend fun getActors() : Resource<List<ActorDto>>
    suspend fun getActorDetail(id: Int) : Resource<ActorDto>
}