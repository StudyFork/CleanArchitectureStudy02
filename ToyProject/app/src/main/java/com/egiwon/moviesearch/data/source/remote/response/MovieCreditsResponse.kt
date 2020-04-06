package com.egiwon.moviesearch.data.source.remote.response

import com.egiwon.moviesearch.data.model.MovieCastEntity
import com.egiwon.moviesearch.data.model.MovieCreditEntity
import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<MovieCast>
)

data class MovieCast(
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)

fun MovieCreditsResponse.mapToMovieCreditEntity(): MovieCreditEntity =
    runCatching {
        MovieCreditEntity(
            id = id,
            cast = cast.map { it.mapToMovieCastEntity() }
        )
    }.getOrNull() ?: MovieCreditEntity()

fun MovieCast.mapToMovieCastEntity(): MovieCastEntity =
    MovieCastEntity(
        castId = castId,
        name = name,
        profilePath = profilePath
    )
