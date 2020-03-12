package com.template.nanamare.network.api

import com.template.nanamare.BuildConfig
import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreAPI {
    @GET("3/genre/movie/list")
    fun requestGenre(
        @Query("api_key") secretKey: String = BuildConfig.SECRET_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE
    ): Single<Response<GenreResponse>>
}