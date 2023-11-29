package com.ps.data.usecase

import com.ps.common.utils.Resource
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.repository.ActorsRepository
import com.ps.domain.model.ActorsModel
import com.ps.domain.usecase.GetActorsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActorsUseCaseImpl @Inject constructor(
    private val getActorsRepository: ActorsRepository,
    private val actorsMapper: ActorsDomainModelMapper,
) : GetActorsUseCase {
    override suspend operator fun invoke(): Resource<ActorsModel> {
        return withContext(Dispatchers.IO) {
            when (val resource = getActorsRepository.getActors()) {
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