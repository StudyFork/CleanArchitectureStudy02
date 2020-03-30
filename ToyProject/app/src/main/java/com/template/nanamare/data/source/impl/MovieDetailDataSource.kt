package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Response

interface MovieDetailDataSource {
    fun getMovieDetail(creditId: Int) : Single<Response<MovieDetailResponse>>
}