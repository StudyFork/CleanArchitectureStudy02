package com.example.movieapplication.domain.model

import com.example.movieapplication.presenter.model.MovieItem

data class MovieEntity(
    val totalPages: Int = 0,
    val movies: List<MovieItemEntity> = listOf()
) {
    data class MovieItemEntity(
        val id: Int = -1,
        val title: String = "",
        val posterPath: String = "",
        val releaseDate: String = ""
    )
}

fun MovieEntity.MovieItemEntity.mapToPresenter(width: Int, height: Int) = MovieItem(
    id = id,
    title = title,
    posterPath = posterPath,
    releaseDate = releaseDate,
    width = width,
    height = height
)
