package com.ps.data.mapper

import com.ps.domain.model.DomainModel


interface ResponseToDomainModelMapper <R: Any, U: DomainModel> {
    fun mapToDomainModel(responseModel: R?): U
}