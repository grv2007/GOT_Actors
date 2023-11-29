package com.ps.data.mapper

import com.ps.common.extensions.ResponseToDomainModelMapper
import com.ps.data.model.ActorResponse
import com.ps.domain.model.Actor
import com.ps.domain.model.ActorsModel
import javax.inject.Inject

class ActorsDomainModelMapper @Inject constructor() :
    ResponseToDomainModelMapper<List<ActorResponse>, ActorsModel> {
    override fun mapToDomainModel(responseModel: List<ActorResponse>?): ActorsModel {
        val actorsResponse = requireNotNull(responseModel)
        val list = mutableListOf<Actor>()
        actorsResponse.forEach { actor ->
            list.add(
                Actor(
                    id = actor.id,
                    fullName = actor.fullName,
                    family = actor.family,
                    imageUrl = actor.imageUrl
                )
            )
        }
        return ActorsModel(list = list)
    }
}