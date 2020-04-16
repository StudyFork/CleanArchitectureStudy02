package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.DetailDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.movie.CreditAPI
import com.template.nanamare.network.response.DetailResponse
import io.reactivex.Single
import retrofit2.Response

class DetailRemoteDataSourceImpl(private val creditAPI: CreditAPI) :
    DetailDataSource {
    override fun getMovieDetail(creditId: Int): Single<Response<DetailResponse>> {
        return creditAPI.requestMovieDetail(creditId).networkDispatchToMain()
    }
}