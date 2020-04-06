package com.egiwon.moviesearch.ui.model

import com.egiwon.moviesearch.data.model.MovieCastEntity

data class MovieDetailViewObject(
    val id: Int = 0,
    val overview: String = "",
    val voteAverage: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val runtime: Int = 0,
    val castList: List<MovieCastEntity> = emptyList()
)