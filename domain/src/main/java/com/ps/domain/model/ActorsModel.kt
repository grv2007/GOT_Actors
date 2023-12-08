package com.ps.domain.model

data class ActorsModel(
    val list: List<Actor>
): DomainModel

data class Actor(
    val id: Int,
    val fullName: String,
    val family: String,
    val imageUrl: String
): DomainModel