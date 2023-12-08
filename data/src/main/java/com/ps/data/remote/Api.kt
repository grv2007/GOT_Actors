package com.ps.data.remote

import com.ps.data.dto.ActorDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("Characters")
    suspend fun getActors() : Response<List<ActorDto>>

    @GET("Characters/{id}")
    suspend fun getActorDetail(@Path("id") id: Int) : Response<ActorDto>
}