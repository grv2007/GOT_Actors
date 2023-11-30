package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.extensions.handleAPICall
import com.ps.data.model.ActorResponse
import com.ps.data.remote.datasource.DataSource
import javax.inject.Inject

class ActorDetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : ActorDetailRepository{
    override suspend fun getActorDetail(id: Int): Resource<ActorResponse> {
        return handleAPICall {dataSource.getActorDetail(id)}
    }
}