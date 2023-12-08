package com.ps.domain.repository


import com.ps.domain.model.ActorsModel
import com.ps.domain.utils.Resource

interface ActorsRepository {
    suspend fun getActors(): Resource<ActorsModel>
}