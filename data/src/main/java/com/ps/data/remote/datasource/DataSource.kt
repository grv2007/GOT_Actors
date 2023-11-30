package com.ps.data.remote.datasource

import com.ps.data.model.ActorResponse
import retrofit2.Response
import retrofit2.http.Path

interface DataSource {
    suspend fun getActors() : Response<List<ActorResponse>>
    suspend fun getActorDetail(id: Int) : Response<ActorResponse>
}