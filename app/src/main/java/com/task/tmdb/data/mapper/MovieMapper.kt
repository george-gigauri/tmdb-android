package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.PopularShowDto
import com.task.tmdb.domain.model.Movie
import java.text.SimpleDateFormat
import java.util.Locale

fun PopularShowDto.toDomain(): Movie {
    return Movie(
        id,
        name,
        try {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(firstAirDate)
        } catch (e: Exception) {
            null
        },
        genreIds,
        originCountry,
        originalLanguage,
        originalName,
        originalLanguage,
        popularity,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W200}$posterPath",
        voteAverage,
        voteCount
    )
}