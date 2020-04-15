package com.egiwon.moviesearch.ui.model

data class MovieTrailerViewObject(
    val id: Int,
    val trailers: List<MovieTrailer>
)

data class MovieTrailer(
    val trailerId: String = "",
    val name: String = "",
    val key: String = "",
    val site: String = ""
)