package com.template.nanamare.data.source

import android.annotation.SuppressLint
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.GenreAPI
import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response

@Suppress("UnstableApiUsage")
@SuppressLint("CheckResult")
class GenreRemoteDataSourceImpl(private val genreAPI: GenreAPI) : GenreDataSource {
    override fun requestGenre(): Single<Response<GenreResponse>> {
        return genreAPI.requestGenre().networkDispatchToMain()
    }
}