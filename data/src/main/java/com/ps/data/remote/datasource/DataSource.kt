package com.ps.data.remote.datasource

import com.ps.data.model.ActorResponse
import retrofit2.Response

interface DataSource {
    suspend fun getActors() : Response<List<ActorResponse>>
}