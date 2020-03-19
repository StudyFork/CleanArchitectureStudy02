package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.ui.model.MovieDetailViewObject

data class MovieDetailEntity(
    val id: Int = 0,
    val overview: String = "",
    val voteAverage: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val runtime: Int = 0
)

fun MovieDetailEntity.mapToMovieDetailViewObject(): MovieDetailViewObject =
    runCatching {
        MovieDetailViewObject(
            id = id,
            overview = overview,
            voteAverage = voteAverage,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            runtime = runtime
        )
    }.getOrNull() ?: MovieDetailViewObject()