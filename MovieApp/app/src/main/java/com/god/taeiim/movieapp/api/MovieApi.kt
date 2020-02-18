package com.god.taeiim.movieapp.api

import com.god.taeiim.movieapp.api.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getMoviePopular(@Query("page") page: Int): Call<MoviePopular>
}