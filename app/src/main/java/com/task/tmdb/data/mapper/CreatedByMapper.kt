package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.CreatedByDto
import com.task.tmdb.domain.model.CreatedBy

fun CreatedByDto.toDomain(): CreatedBy {
    return CreatedBy(
        id,
        name,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W200}$profilePath"
    )
}