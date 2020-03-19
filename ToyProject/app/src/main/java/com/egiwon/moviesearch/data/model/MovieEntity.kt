package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.ui.model.MovieViewObject

data class MovieEntity(
    override val id: Int = 0,
    val title: String = "",
    val posterPath: String = ""
) : BaseIdentifier()

fun MovieEntity.mapToMovieViewObject(): MovieViewObject = MovieViewObject(
    id = id,
    title = title,
    posterPath = posterPath
)
