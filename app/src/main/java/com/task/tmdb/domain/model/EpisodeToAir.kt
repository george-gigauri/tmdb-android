package com.task.tmdb.domain.model

import java.util.Date

data class EpisodeToAir(
    val id: Int,
    val name: String,
    val overview: String,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val airDate: Date,
    val voteAverage: Double,
    val voteCount: Int
)