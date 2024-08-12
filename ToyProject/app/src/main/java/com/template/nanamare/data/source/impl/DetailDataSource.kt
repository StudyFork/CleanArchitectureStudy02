package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.DetailResponse
import io.reactivex.Single
import retrofit2.Response

interface DetailDataSource {
    fun getMovieDetail(creditId: Int) : Single<Response<DetailResponse>>
}