package com.ps.common.extensions

import com.ps.common.model.DomainModel

interface ResponseToDomainModelMapper <R: Any, U: DomainModel> {
    fun mapToDomainModel(responseModel: R?): U?
}