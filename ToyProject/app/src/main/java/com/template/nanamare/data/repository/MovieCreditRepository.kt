package com.template.nanamare.data.repository

import com.template.nanamare.data.source.MovieCreditRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.MovieCreditDataSource
import com.template.nanamare.network.response.MovieCreditResponse
import io.reactivex.Single
import retrofit2.Response

class MovieCreditRepository(private val movieCreditRemoteDataSourceImpl: MovieCreditRemoteDataSourceImpl) :
    MovieCreditDataSource {
    override fun getMovieCredit(movieId: Int): Single<Response<MovieCreditResponse>> {
        return movieCreditRemoteDataSourceImpl.getMovieCredit(movieId)
    }
}