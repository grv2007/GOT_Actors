package com.ps.domain.model

import com.ps.common.model.DomainModel

data class ActorsModel(
    val list: List<Actor>
): DomainModel

data class Actor(
    val id: Int,
    val fullName: String,
    val family: String,
    val imageUrl: String
): DomainModel