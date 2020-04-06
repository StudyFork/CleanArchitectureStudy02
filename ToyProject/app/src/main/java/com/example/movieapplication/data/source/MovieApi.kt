package com.example.movieapplication.data.source

import com.example.movieapplication.data.model.MovieDetailResponse
import com.example.movieapplication.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): MovieResponse

    @GET("{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetailResponse
}