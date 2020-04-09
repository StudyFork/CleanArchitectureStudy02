package com.god.taeiim.movieapp.data.source

import com.god.taeiim.movieapp.data.model.MoviePopular
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("page") page: Int): MoviePopular
}