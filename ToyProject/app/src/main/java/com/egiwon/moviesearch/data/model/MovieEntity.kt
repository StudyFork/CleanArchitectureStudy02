package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.ui.vo.MovieViewObject

data class MovieEntity(
    override val id: Int = 0,
    val title: String = "",
    val population: Double = 0.0,
    val posterPath: String = "",
    val overView: String = ""
) : BaseIdentifier()

fun MovieEntity.mapToMovieViewObject(): MovieViewObject = MovieViewObject(
    id = id,
    title = title,
    population = population,
    posterPath = posterPath,
    overView = overView
)
