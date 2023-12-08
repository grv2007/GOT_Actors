package com.ps.data.mapper

import com.ps.domain.model.DomainModel


interface DtoToDomainModelMapper <R: Any, U: DomainModel> {
    fun mapToDomainModel(dto: R): U
}