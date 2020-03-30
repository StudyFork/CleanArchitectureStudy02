package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.MovieDetailDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.MovieDetailAPI
import com.template.nanamare.network.response.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Response

class MovieDetailRemoteDataSourceImpl(private val movieDetailAPI: MovieDetailAPI) :
    MovieDetailDataSource {
    override fun getMovieDetail(creditId: Int): Single<Response<MovieDetailResponse>> {
        return movieDetailAPI.requestMovieDetail(creditId).networkDispatchToMain()
    }
}