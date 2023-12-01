package com.ps.domain.usecase

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.repository.ActorDetailRepository
import javax.inject.Inject

class GetActorDetailUseCase @Inject constructor(
    private val getActorDetailRepository: ActorDetailRepository
)  {
    suspend operator fun invoke(id: Int): Resource<ActorDetailModel> {
        return getActorDetailRepository.getActorDetail(id)
    }
}