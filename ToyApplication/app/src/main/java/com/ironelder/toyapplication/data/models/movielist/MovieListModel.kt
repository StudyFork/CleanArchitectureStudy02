package com.ironelder.toyapplication.data.models.movielist

import com.google.gson.annotations.SerializedName

data class MovieListModel(
    val page: Int,
    @SerializedName("results")
    val movieResultModels: List<MovieResultModel>,
    val total_pages: Int,
    val total_results: Int
)
//
//fun MovieListModel.mappingEntity():MovieListModel = MovieListModel(
//    page = page,
//    total_pages = total_pages,
//    total_results = total_results,
//    movieResultModels = movieResultModels.map {
//        MovieResultModel(
//            adult = it.adult,
//            backdrop_path = it.backdrop_path,
//            genre_ids = it.genre_ids,
//            id = it.id,
//            original_language = it.original_language,
//            original_title = it.original_title,
//            overview = it.overview,
//            popularity = it.popularity,
//            poster_path = IMAGE_BASE_URL + it.poster_path,
//            release_date = it.release_date,
//            title = it.title,
//            video = it.video,
//            vote_average = it.vote_average,
//            vote_count = it.vote_count
//        )
//    }
//)