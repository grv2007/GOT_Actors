package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.extensions.handleAPICall
import com.ps.data.model.ActorResponse
import com.ps.data.remote.datasource.DataSource
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : ActorsRepository {
    override suspend fun getActors(): Resource<List<ActorResponse>> {
        return handleAPICall { dataSource.getActors() }
    }
}