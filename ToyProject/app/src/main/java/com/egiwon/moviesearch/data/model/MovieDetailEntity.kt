package com.egiwon.moviesearch.data.model

data class MovieDetailEntity(
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String
)