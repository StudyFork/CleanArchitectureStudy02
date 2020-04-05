package com.example.movieapplication.presenter.model

data class Movie(
    val totalPages: Int = -1,
    val movies: List<MovieItem> = listOf()
)