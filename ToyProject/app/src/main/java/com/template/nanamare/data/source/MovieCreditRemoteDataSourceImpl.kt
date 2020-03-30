package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.MovieCreditDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.MovieCreditAPI
import com.template.nanamare.network.response.MovieCreditResponse
import io.reactivex.Single
import retrofit2.Response

class MovieCreditRemoteDataSourceImpl(private val movieCreditAPI: MovieCreditAPI) :
    MovieCreditDataSource {
    override fun getMovieCredit(movieId: Int): Single<Response<MovieCreditResponse>> {
        return movieCreditAPI.requestMovieCredit(movieId).networkDispatchToMain()
    }
}