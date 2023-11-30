package com.ps.data.usecase

import com.ps.common.utils.Resource
import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.repository.ActorDetailRepository
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.usecase.GetActorDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActorDetailUseCaseImpl @Inject constructor(
    private val getActorDetailRepository: ActorDetailRepository,
    private val actorDetailMapper: ActorDetailDomainModelMapper,
) : GetActorDetailUseCase {
    override suspend fun invoke(id: Int): Resource<ActorDetailModel> {
        return withContext(Dispatchers.IO) {
            when (val resource = getActorDetailRepository.getActorDetail(id)) {
                is Resource.Success -> {
                    Resource.Success(actorDetailMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}