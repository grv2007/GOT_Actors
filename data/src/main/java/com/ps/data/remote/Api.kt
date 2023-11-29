package com.ps.data.remote

import com.ps.data.model.ActorResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("Characters")
    suspend fun getActors() : Response<List<ActorResponse>>
}