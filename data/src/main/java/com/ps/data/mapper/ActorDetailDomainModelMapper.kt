package com.ps.data.mapper

import com.ps.data.dto.ActorDto
import com.ps.domain.model.ActorDetailModel
import javax.inject.Inject

class ActorDetailDomainModelMapper @Inject constructor() :
    DtoToDomainModelMapper<ActorDto, ActorDetailModel> {
    override fun mapToDomainModel(dto: ActorDto) =  ActorDetailModel(
            id = dto.id ?: 0,
            firstName = dto.firstName?: "",
            lastName = dto.lastName?: "",
            fullName = dto.fullName?: "",
            title = dto.title?: "",
            family = dto.family?: "",
            imageUrl = dto.imageUrl?: ""
        )
}