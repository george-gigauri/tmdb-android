package com.task.tmdb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularShowsResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<PopularShowDto>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)