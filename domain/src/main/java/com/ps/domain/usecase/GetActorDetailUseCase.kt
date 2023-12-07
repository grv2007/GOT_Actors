package com.ps.domain.usecase


import com.ps.domain.di.IoDispatcher
import com.ps.domain.repository.ActorDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActorDetailUseCase @Inject constructor(
    private val getActorDetailRepository: ActorDetailRepository,
     @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int) =
        withContext(dispatcher) {
            getActorDetailRepository.getActorDetail(id)
        }
}
