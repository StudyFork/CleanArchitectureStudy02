package com.example.movieapplication.domain.model

data class MovieDetailEntity(
    val id: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)