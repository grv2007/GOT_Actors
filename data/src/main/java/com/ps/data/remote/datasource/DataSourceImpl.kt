package com.ps.data.remote.datasource

import com.ps.data.dto.ActorDto
import com.ps.data.extensions.handleAPICall
import com.ps.data.remote.Api
import com.ps.domain.utils.Resource
import javax.inject.Inject

internal class DataSourceImpl @Inject constructor(
    private val api: Api
) : DataSource {
    override suspend fun getActors(): Resource<List<ActorDto>> {
        return handleAPICall {api.getActors()}
    }

    override suspend fun getActorDetail(id: Int): Resource<ActorDto> {
        return handleAPICall {api.getActorDetail(id)}
    }
}