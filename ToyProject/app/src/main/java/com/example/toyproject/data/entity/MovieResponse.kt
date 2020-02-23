package com.example.toyproject.data.entity

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("searchMovieResults")
    val results: List<SearchMovieData>
)

data class SearchMovieData(
    val id: String,
    val posterPath: String
)