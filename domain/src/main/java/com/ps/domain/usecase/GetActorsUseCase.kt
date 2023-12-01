package com.ps.domain.usecase

import com.ps.common.di.IoDispatcher
import com.ps.common.utils.Resource
import com.ps.domain.model.ActorsModel
import com.ps.domain.repository.ActorsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActorsUseCase @Inject constructor(
    private val getActorsRepository: ActorsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
)  {
    suspend operator fun invoke(): Resource<ActorsModel> {
        return withContext(dispatcher) {
            getActorsRepository.getActors()
        }
    }
}