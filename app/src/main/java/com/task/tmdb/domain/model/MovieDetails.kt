package com.task.tmdb.domain.model

import java.util.Date

data class MovieDetails(
    val id: Int,
    val name: String,
    val isAdult: Boolean,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: Date,
    val genres: List<Genre>,
    val homepage: String,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: Date,
    val lastEpisodeToAir: EpisodeToAir?,
    val networks: List<Network>,
    val nextEpisodeToAir: EpisodeToAir?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)
