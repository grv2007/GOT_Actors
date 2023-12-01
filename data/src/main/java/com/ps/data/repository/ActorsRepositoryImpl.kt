package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.extensions.handleAPICall
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.model.ActorResponse
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.model.ActorsModel
import com.ps.domain.repository.ActorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val actorsMapper: ActorsDomainModelMapper
) : ActorsRepository {
    override suspend fun getActors(): Resource<ActorsModel> {
        return withContext(Dispatchers.IO) {
            when (val resource = handleAPICall {dataSource.getActors()}) {
                is Resource.Success -> {
                    Resource.Success(actorsMapper.mapToDomainModel(resource.data))
                }
                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}