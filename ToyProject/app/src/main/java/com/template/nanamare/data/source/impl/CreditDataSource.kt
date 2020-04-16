package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.CreditResponse
import io.reactivex.Single
import retrofit2.Response

interface CreditDataSource {
    fun getMovieCredit(movieId: Int) : Single<Response<CreditResponse>>
}