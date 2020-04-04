package com.template.nanamare.network.api.movie

import com.template.nanamare.network.response.CreditResponse
import com.template.nanamare.network.response.VideoResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {

    @GET("3/movie/{movie_id}/videos")
    fun requestMovieVideos(@Path("movie_id") movieId: Int): Single<Response<VideoResponse>>

    @GET("3/movie/{movie_id}/credits")
    fun requestMovieCredit(@Path("movie_id") movieId: Int): Single<Response<CreditResponse>>
}