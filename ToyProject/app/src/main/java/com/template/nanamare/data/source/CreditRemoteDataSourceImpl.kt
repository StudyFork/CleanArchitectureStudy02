package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.CreditDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.movie.MovieAPI
import com.template.nanamare.network.response.CreditResponse
import io.reactivex.Single
import retrofit2.Response

class CreditRemoteDataSourceImpl(private val movieAPI: MovieAPI) :
    CreditDataSource {
    override fun getMovieCredit(movieId: Int): Single<Response<CreditResponse>> {
        return movieAPI.requestMovieCredit(movieId).networkDispatchToMain()
    }
}