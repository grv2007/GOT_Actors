package com.ps.domain.repository

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorDetailModel

interface ActorDetailRepository {
    suspend fun getActorDetail(id: Int) : Resource<ActorDetailModel>
}