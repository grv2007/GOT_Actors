package com.ps.domain.usecase

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorsModel

interface GetActorsUseCase {
    suspend operator fun invoke(): Resource<ActorsModel>
}