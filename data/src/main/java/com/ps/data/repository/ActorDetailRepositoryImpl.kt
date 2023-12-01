package com.ps.data.repository

import com.ps.common.utils.Resource
import com.ps.data.extensions.handleAPICall
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.repository.ActorDetailRepository
import javax.inject.Inject

class ActorDetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val actorDetailMapper: ActorDetailDomainModelMapper
) : ActorDetailRepository {
    override suspend fun getActorDetail(id: Int): Resource<ActorDetailModel> {
        return when (val resource = handleAPICall { dataSource.getActorDetail(id) }) {
            is Resource.Success -> {
                Resource.Success(actorDetailMapper.mapToDomainModel(resource.data))
            }

            is Resource.Failure -> {
                Resource.Failure(resource.error)
            }
        }
    }

}