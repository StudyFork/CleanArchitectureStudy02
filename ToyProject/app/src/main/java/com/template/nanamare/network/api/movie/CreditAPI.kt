package com.template.nanamare.network.api.movie

import com.template.nanamare.network.response.DetailResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditAPI {
    @GET("3/credit/{credit_id}")
    fun requestMovieDetail(@Path("credit_id") creditId: Int): Single<Response<DetailResponse>>
}