package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.model.ActorResponse

interface ActorsRepository {
    suspend fun getActors(): Resource<List<ActorResponse>>
}