package com.template.nanamare.network.api

import com.template.nanamare.network.response.MovieCreditResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieCreditAPI {
    @GET("3/movie/{movie_id}/credits")
    fun requestMovieCredit(@Path("movie_id") movieId: Int): Single<Response<MovieCreditResponse>>
}