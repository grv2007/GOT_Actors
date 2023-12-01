package com.ps.domain.usecase

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorsModel
import com.ps.domain.repository.ActorsRepository
import javax.inject.Inject

class GetActorsUseCase @Inject constructor(
    private val getActorsRepository: ActorsRepository
)  {
    suspend operator fun invoke(): Resource<ActorsModel> {
        return getActorsRepository.getActors()
    }
}