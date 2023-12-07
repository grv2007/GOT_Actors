package com.ps.data.mapper

import com.ps.data.dto.ActorDto
import com.ps.domain.model.Actor
import com.ps.domain.model.ActorsModel
import javax.inject.Inject

class ActorsDomainModelMapper @Inject constructor() :
    DtoToDomainModelMapper<List<ActorDto>, ActorsModel> {
    override fun mapToDomainModel(dto: List<ActorDto>) = ActorsModel(
        list = dto.map {
            Actor(
                id = it.id?:0,
                fullName = it.fullName?: "",
                family = it.family?: "",
                imageUrl = it.imageUrl?: ""
            )
        }
    )

}