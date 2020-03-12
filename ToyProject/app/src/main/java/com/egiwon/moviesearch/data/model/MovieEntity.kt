package com.egiwon.moviesearch.data.model

data class MovieEntity(
    val id: Int,
    val title: String,
    val population: Double,
    val posterPath: String,
    val overView: String
)
