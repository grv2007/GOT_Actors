package com.ps.domain.usecase

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.model.ActorsModel

interface GetActorDetailUseCase {
    suspend operator fun invoke(id: Int): Resource<ActorDetailModel>
}