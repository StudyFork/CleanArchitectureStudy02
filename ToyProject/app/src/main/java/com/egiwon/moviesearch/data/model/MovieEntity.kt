package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.ui.vo.MovieViewObject

data class MovieEntity(
    val id: Int,
    val title: String,
    val population: Double,
    val posterPath: String,
    val overView: String
)

fun MovieEntity.mapToMovieViewObject(): MovieViewObject = MovieViewObject(
    id = id,
    title = title,
    population = population,
    posterPath = posterPath,
    overView = overView
)
