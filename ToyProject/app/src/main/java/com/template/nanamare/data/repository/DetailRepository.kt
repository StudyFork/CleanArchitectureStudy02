package com.template.nanamare.data.repository

import com.template.nanamare.data.source.DetailRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.DetailDataSource
import com.template.nanamare.network.response.DetailResponse
import io.reactivex.Single
import retrofit2.Response

class DetailRepository(private val detailRemoteDataSourceImpl: DetailRemoteDataSourceImpl) :
    DetailDataSource {
    override fun getMovieDetail(creditId: Int): Single<Response<DetailResponse>> {
        return detailRemoteDataSourceImpl.getMovieDetail(creditId)
    }
}