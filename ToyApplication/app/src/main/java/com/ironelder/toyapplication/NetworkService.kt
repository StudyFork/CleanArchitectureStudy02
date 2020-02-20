package com.ironelder.toyapplication

import com.ironelder.toyapplication.model.movielist.MovieListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("{type}")
    fun getMovieList(@Path("type") type: String, @Query("page") page: Int? = 1, @Query("api_key") api_key: String = API_KEY): Call<MovieListModel>
}