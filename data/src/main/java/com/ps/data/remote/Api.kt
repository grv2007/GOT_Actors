package com.ps.data.remote

import com.ps.data.model.ActorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("Characters")
    suspend fun getActors() : Response<List<ActorResponse>>

    @GET("Characters/{id}")
    suspend fun getActorDetail(@Path("id") id: Int) : Response<ActorResponse>
}