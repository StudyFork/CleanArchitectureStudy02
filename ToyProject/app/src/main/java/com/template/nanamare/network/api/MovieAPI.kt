package com.template.nanamare.network.api

import com.template.nanamare.network.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/discover/movie")
    fun requestMovies(
        @Query("with_genres") withGenres: String
    ): Single<Response<MovieResponse>>

    @GET("3/search/movie")
    fun searchMovies(
        @Query("query") query: String
    ): Single<Response<MovieResponse>>

}