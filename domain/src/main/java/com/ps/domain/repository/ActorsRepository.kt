package com.ps.domain.repository

import com.ps.common.utils.Resource
import com.ps.domain.model.ActorsModel

interface ActorsRepository {
    suspend fun getActors(): Resource<ActorsModel>
}