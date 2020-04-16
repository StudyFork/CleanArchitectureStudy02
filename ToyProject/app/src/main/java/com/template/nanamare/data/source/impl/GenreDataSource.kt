package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response

interface GenreDataSource {
    fun requestGenre() : Single<Response<GenreResponse>>
}