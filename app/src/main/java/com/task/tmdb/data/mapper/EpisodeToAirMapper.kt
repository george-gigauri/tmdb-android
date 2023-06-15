package com.task.tmdb.data.mapper

import com.task.tmdb.data.remote.dto.EpisodeToAirDto
import com.task.tmdb.domain.model.EpisodeToAir
import java.text.SimpleDateFormat

fun EpisodeToAirDto.toDomain(): EpisodeToAir {
    return EpisodeToAir(
        id,
        name,
        overview,
        seasonNumber,
        episodeNumber,
        SimpleDateFormat("yyyy-MM-dd").parse(airDate),
        voteAverage,
        voteCount
    )
}
