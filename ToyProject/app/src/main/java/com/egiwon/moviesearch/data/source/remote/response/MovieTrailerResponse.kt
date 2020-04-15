package com.egiwon.moviesearch.data.source.remote.response

import com.egiwon.moviesearch.data.model.MovieTrailerEntity
import com.egiwon.moviesearch.data.model.MovieTrailerItem
import com.google.gson.annotations.SerializedName

data class MovieTrailerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieTrailerResponseItem>
)

data class MovieTrailerResponseItem(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("site")
    val site: String = "",
    @SerializedName("key")
    val key: String = ""
)

fun MovieTrailerResponse.mapToMovieTrailerEntity(): MovieTrailerEntity =
    MovieTrailerEntity(
        id = id,
        trailers = results.map { it.mapToMovieTrailerEntityItem() }
    )

fun MovieTrailerResponseItem.mapToMovieTrailerEntityItem(): MovieTrailerItem =
    MovieTrailerItem(
        trailerId = id,
        name = name,
        key = key,
        site = site
    )