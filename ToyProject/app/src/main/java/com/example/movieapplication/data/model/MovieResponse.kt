package com.example.movieapplication.data.model


import com.example.movieapplication.presenter.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<Result> = listOf()
) {
    data class Result(
        @SerializedName("popularity")
        val popularity: Double = 0.0,
        @SerializedName("vote_count")
        val voteCount: Int = 0,
        @SerializedName("video")
        val video: Boolean = false,
        @SerializedName("poster_path")
        val posterPath: String? = "",
        @SerializedName("id")
        val id: Int = -1,
        @SerializedName("adult")
        val adult: Boolean = false,
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        @SerializedName("original_language")
        val originalLanguage: String = "",
        @SerializedName("original_title")
        val originalTitle: String = "",
        @SerializedName("genre_ids")
        val genreIds: List<Int> = emptyList(),
        @SerializedName("title")
        val title: String = "",
        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("release_date")
        val releaseDate: String = ""
    )
}

fun MovieResponse.Result.mapToPresenter(width: Int, height: Int) = MovieItem(
    id = id,
    title = title,
    posterPath = if (posterPath.isNullOrEmpty()) {
        ""
    } else {
        posterPath
    },
    releaseDate = releaseDate,
    width = width,
    height = height
)