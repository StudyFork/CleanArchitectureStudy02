package com.ironelder.toyapplication.data.service

import com.ironelder.toyapplication.common.utils.API_KEY
import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    //TODO : After Change Call -> Deferred
    @GET("movie/{type}")
    fun getPopularMovieList(
        @Path("type") type: String,
        @Query("page") page: Int? = 1,
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieListModel>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("include_adult") adult: Boolean? = false,
        @Query("api_key") api_key: String = API_KEY
    ): MovieListModel
}