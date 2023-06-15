package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.SeasonDto
import com.task.tmdb.domain.model.Season
import java.text.SimpleDateFormat

fun SeasonDto.toDomain(): Season {
    return Season(
        id,
        name,
        seasonNumber,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W400}$posterPath",
        overview,
        episodeCount,
        SimpleDateFormat("yyyy-MM-dd").parse(airDate)
    )
}