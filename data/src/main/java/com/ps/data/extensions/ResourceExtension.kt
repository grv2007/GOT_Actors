package com.ps.data.extensions

import com.ps.data.mapper.DtoToDomainModelMapper
import com.ps.domain.model.DomainModel
import com.ps.domain.utils.Resource

fun <R: Any, U: DomainModel> Resource<R>.convertDtoToDomain(mapper: DtoToDomainModelMapper<R,U>):
    Resource<U> {
    return when (this) {
        is Resource.Success -> {
            Resource.Success(mapper.mapToDomainModel(this.data))
        }
        is Resource.Failure -> {
            Resource.Failure(this.error)
        }
    }
}