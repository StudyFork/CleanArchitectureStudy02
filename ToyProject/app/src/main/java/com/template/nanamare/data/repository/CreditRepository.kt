package com.template.nanamare.data.repository

import com.template.nanamare.data.source.CreditRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.CreditDataSource
import com.template.nanamare.network.response.CreditResponse
import io.reactivex.Single
import retrofit2.Response

class CreditRepository(private val creditRemoteDataSourceImpl: CreditRemoteDataSourceImpl) :
    CreditDataSource {
    override fun getMovieCredit(movieId: Int): Single<Response<CreditResponse>> {
        return creditRemoteDataSourceImpl.getMovieCredit(movieId)
    }
}