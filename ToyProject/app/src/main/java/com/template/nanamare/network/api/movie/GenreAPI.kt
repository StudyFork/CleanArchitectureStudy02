package com.template.nanamare.network.api.movie

import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface GenreAPI {
    @GET("3/genre/movie/list")
    fun requestGenre(
    ): Single<Response<GenreResponse>>
}