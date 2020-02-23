package com.example.toyproject.network

import com.example.toyproject.data.entity.SearchMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    val APP_KEY: String
        get() = "e29f73abf94a892a99c8df777d038279"

    @GET("/search/movie")
    fun requestSearchMovie(
        api_key: String = APP_KEY,
        query: String,
        page: Int
    ): Call<SearchMovieResponse>
}