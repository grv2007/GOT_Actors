package com.ps.data.repository

import com.ps.data.extensions.convertDtoToDomain
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.domain.repository.ActorsRepository
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val actorsMapper: ActorsDomainModelMapper
) : ActorsRepository {
    override suspend fun getActors() = dataSource.getActors().convertDtoToDomain(actorsMapper)
}