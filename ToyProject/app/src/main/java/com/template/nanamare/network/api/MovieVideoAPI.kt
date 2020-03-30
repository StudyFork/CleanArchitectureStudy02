package com.template.nanamare.network.api

import com.template.nanamare.network.response.MovieVideoResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieVideoAPI {
    @GET("3/movie/{movie_id}/videos")
    fun requestMovieVideos(@Path("movie_id") movieId: Int): Single<Response<MovieVideoResponse>>
}