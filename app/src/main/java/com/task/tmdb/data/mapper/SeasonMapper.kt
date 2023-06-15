package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.SeasonDto
import com.task.tmdb.domain.model.Season
import java.text.SimpleDateFormat
import java.util.Locale

fun SeasonDto.toDomain(): Season {
    return Season(
        id,
        name,
        seasonNumber,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W400}$posterPath",
        overview,
        episodeCount,
        airDate?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it) }
    )
}