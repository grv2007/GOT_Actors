package com.ps.domain.model

import com.ps.common.model.DomainModel

data class ActorDetailModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val imageUrl: String
): DomainModel