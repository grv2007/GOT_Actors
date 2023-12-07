package com.ps.domain.repository


import com.ps.domain.model.ActorDetailModel
import com.ps.domain.utils.Resource

interface ActorDetailRepository {
    suspend fun getActorDetail(id: Int) : Resource<ActorDetailModel>
}