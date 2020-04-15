package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.ui.model.MovieTrailer
import com.egiwon.moviesearch.ui.model.MovieTrailerViewObject

data class MovieTrailerEntity(
    val id: Int,
    val trailers: List<MovieTrailerItem>
)

data class MovieTrailerItem(
    val trailerId: String = "",
    val name: String = "",
    val key: String = "",
    val site: String = ""
)

fun MovieTrailerEntity.mapToMovieTrailerViewObject(): MovieTrailerViewObject =
    MovieTrailerViewObject(
        id = id,
        trailers = trailers.map { it.mapToMovieTrailerEntityItem() }
    )

fun MovieTrailerItem.mapToMovieTrailerEntityItem(): MovieTrailer =
    MovieTrailer(
        trailerId = trailerId,
        name = name,
        key = key,
        site = site
    )