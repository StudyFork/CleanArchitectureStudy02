package com.mhuman.coroutine.network.api

import com.mhuman.coroutine.network.model.PopularMovieReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?language=ko-KR&sort_by=popularity.desc")
    suspend fun getPopularMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<PopularMovieReponse>

    companion object {
        val API_BASE_URL = "https://api.themoviedb.org/3/"
        val API_BASE_IMAGE_PATH_URL = "https://image.tmdb.org/t/p/w500"
    }
}