package com.ps.data.remote.datasource

import com.ps.data.model.ActorResponse
import com.ps.data.remote.Api
import retrofit2.Response
import javax.inject.Inject

internal class DataSourceImpl @Inject constructor(
    private val api: Api
) : DataSource {
    override suspend fun getActors(): Response<List<ActorResponse>> {
        return api.getActors()
    }

    override suspend fun getActorDetail(id: Int): Response<ActorResponse> {
        return api.getActorDetail(id)
    }
}