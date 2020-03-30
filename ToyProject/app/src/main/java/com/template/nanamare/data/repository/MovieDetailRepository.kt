package com.template.nanamare.data.repository

import com.template.nanamare.data.source.MovieDetailRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.MovieDetailDataSource
import com.template.nanamare.network.response.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Response

class MovieDetailRepository(private val movieDetailRemoteDataSourceImpl: MovieDetailRemoteDataSourceImpl) :
    MovieDetailDataSource {
    override fun getMovieDetail(creditId: Int): Single<Response<MovieDetailResponse>> {
        return movieDetailRemoteDataSourceImpl.getMovieDetail(creditId)
    }
}