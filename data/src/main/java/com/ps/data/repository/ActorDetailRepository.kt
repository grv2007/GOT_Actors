package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.model.ActorResponse

interface ActorDetailRepository {
    suspend fun getActorDetail(id: Int) : Resource<ActorResponse>
}