package com.example.movieapplication.data.source

import com.example.movieapplication.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): MovieResponse
}