package com.example.toyproject.data.entity

import com.example.toyproject.network.NetworkConstant
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("results")
    val searchMovieResults: List<SearchMovieData>
)

data class SearchMovieData(
    @SerializedName("id")
    val movieId: String,
    @SerializedName("poster_path")
    val posterPath: String
) {
    fun getPosterUrl(): String = "${NetworkConstant.IMAGE_PATH}$posterPath"

    fun onClickItem() {
        // TODO: 2020-03-23
    }
}