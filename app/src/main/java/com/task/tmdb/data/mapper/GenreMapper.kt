package com.task.tmdb.data.mapper

import com.task.tmdb.data.remote.dto.GenreDto
import com.task.tmdb.domain.model.Genre

fun GenreDto.toDomain(): Genre {
    return Genre(
        name
    )
}