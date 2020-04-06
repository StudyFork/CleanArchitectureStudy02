package com.egiwon.moviesearch.data.model

data class MovieCreditEntity(
    val id: Int = 0,
    val cast: List<MovieCastEntity> = emptyList()
)

data class MovieCastEntity(
    val castId: Int = 0,
    val name: String = "",
    val profilePath: String? = ""
)
