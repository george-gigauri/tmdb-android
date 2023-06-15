package com.task.tmdb.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.task.tmdb.common.Const
import com.task.tmdb.domain.model.Network

data class NetworkDto(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)