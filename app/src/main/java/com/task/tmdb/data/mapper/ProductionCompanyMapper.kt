package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.ProductionCompanyDto
import com.task.tmdb.domain.model.ProductionCompany

fun ProductionCompanyDto.toDomain(): ProductionCompany {
    return ProductionCompany(
        id,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W400}$logoPath",
        name
    )
}