package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.MovieCreditResponse
import io.reactivex.Single
import retrofit2.Response

interface MovieCreditDataSource {
    fun getMovieCredit(movieId: Int) : Single<Response<MovieCreditResponse>>
}