package com.task.tmdb.data.mapper

import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.dto.TVShowDetailsDto
import com.task.tmdb.domain.model.MovieDetails
import java.text.SimpleDateFormat

fun TVShowDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id,
        name,
        adult,
        createdBy.map { it.toDomain() },
        episodeRunTime,
        SimpleDateFormat("yyyy-MM-dd").parse(firstAirDate),
        genres.map { it.toDomain() },
        homepage,
        inProduction,
        languages,
        SimpleDateFormat("yyyy-MM-dd").parse(lastAirDate),
        lastEpisodeToAir?.toDomain(),
        networks.map { it.toDomain() },
        nextEpisodeToAir?.toDomain(),
        numberOfEpisodes,
        numberOfSeasons,
        originCountry,
        originalLanguage,
        originalName,
        overview,
        popularity,
        "${Const.TMDB_IMAGE_PATH_PREFIX_W400}$posterPath",
        productionCompanies.map { it.toDomain() },
        productionCountries.map { it.toDomain() },
        seasons.map { it.toDomain() },
        spokenLanguages.map { it.toDomain() },
        status,
        type,
        voteAverage,
        voteCount
    )
}