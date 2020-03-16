package com.example.movieapplication.presenter.model

import com.example.movieapplication.presenter.adapter.MovieAdapter

data class MovieItem(
    val id: Int = -1,
    val title: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val width: Int = -1,
    val height: Int = -1,
    val viewType: MovieAdapter.ViewType = MovieAdapter.ViewType.MOVIE
)