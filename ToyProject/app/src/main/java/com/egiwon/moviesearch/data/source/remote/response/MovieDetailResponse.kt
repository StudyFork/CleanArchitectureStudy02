package com.egiwon.moviesearch.data.source.remote.response

import com.egiwon.moviesearch.data.model.MovieDetailEntity
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String
)

fun MovieDetailResponse.mapToMovieDetailEntity(): MovieDetailEntity = MovieDetailEntity(
    id = id,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title
)