package com.ps.data.mapper

import com.ps.common.extensions.ResponseToDomainModelMapper
import com.ps.data.model.ActorResponse
import com.ps.domain.model.ActorDetailModel
import javax.inject.Inject

class ActorDetailDomainModelMapper @Inject constructor() :
    ResponseToDomainModelMapper<ActorResponse, ActorDetailModel> {
    override fun mapToDomainModel(responseModel: ActorResponse?): ActorDetailModel {
        val actorDetailResponse = requireNotNull(responseModel)
        return ActorDetailModel(
            id = actorDetailResponse.id,
            firstName = actorDetailResponse.firstName,
            lastName = actorDetailResponse.lastName,
            fullName = actorDetailResponse.fullName,
            title = actorDetailResponse.title,
            family = actorDetailResponse.family,
            imageUrl = actorDetailResponse.imageUrl
        )
    }
}