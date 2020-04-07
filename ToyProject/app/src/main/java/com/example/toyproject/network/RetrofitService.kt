package com.example.toyproject.network

import com.example.toyproject.data.entity.SearchMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/movie")
    fun requestSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<SearchMovieResponse>
}