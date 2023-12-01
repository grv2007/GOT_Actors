package com.ps.domain.usecase

import com.ps.common.di.IoDispatcher
import com.ps.common.utils.Resource
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.repository.ActorDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActorDetailUseCase @Inject constructor(
    private val getActorDetailRepository: ActorDetailRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
)  {
    suspend operator fun invoke(id: Int): Resource<ActorDetailModel> {
        return withContext(dispatcher) {
            getActorDetailRepository.getActorDetail(id)
        }
    }
}