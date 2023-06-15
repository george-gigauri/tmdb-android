package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.NetworkDto
import com.task.tmdb.domain.model.Network

fun NetworkDto.toDomain(): Network {
    return Network(
        id,
        name,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W200}$logoPath",
        originCountry
    )
}