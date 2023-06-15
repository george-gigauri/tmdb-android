package com.task.tmdb.domain.model

import java.util.Date

data class Season(
    val id: Int,
    val name: String,
    val seasonNumber: Int,
    val posterPath: String,
    val overview: String,
    val episodeCount: Int,
    val airDate: Date?
)
