package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.ui.model.MovieCastViewObject

data class MovieCreditEntity(
    val id: Int = 0,
    val castList: List<MovieCastEntity> = emptyList()
)

data class MovieCastEntity(
    val castId: Int = 0,
    val name: String = "",
    val profilePath: String? = ""
)

fun MovieCastEntity.mapToMovieCastViewObject(): MovieCastViewObject =
    MovieCastViewObject(
        id = castId,
        name = name,
        profilePath = profilePath
    )
