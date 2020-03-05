package com.example.toyproject.data.entity

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("results")
    val searchMovieResults: List<SearchMovieData>
)

data class SearchMovieData(
    val id: String,
    val poster_path: String
) {
    fun getPosterUrl(): String = "https://image.tmdb.org/t/p/w200/$poster_path"
}