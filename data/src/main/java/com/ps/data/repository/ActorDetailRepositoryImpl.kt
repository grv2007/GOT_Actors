package com.ps.data.repository

import com.ps.data.extensions.convertDtoToDomain
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.repository.ActorDetailRepository
import javax.inject.Inject

class ActorDetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val actorDetailMapper: ActorDetailDomainModelMapper
) : ActorDetailRepository {
    override suspend fun getActorDetail(id: Int) =
        dataSource.getActorDetail(id).convertDtoToDomain(actorDetailMapper)
}