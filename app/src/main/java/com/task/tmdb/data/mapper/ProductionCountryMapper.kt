package com.task.tmdb.data.mapper

import com.task.tmdb.data.remote.dto.ProductionCountryDto
import com.task.tmdb.domain.model.ProductionCountry

fun ProductionCountryDto.toDomain(): ProductionCountry {
    return ProductionCountry(name)
}