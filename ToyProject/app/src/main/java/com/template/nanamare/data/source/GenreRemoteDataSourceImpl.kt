package com.template.nanamare.data.source

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.ext.converterErrorBody
import com.template.nanamare.ext.networkCommunication
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.api.GenreAPI
import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response

@Suppress("UnstableApiUsage")
@SuppressLint("CheckResult")
class GenreRemoteDataSourceImpl(private val genreAPI: GenreAPI) : GenreDataSource {

    override fun requestGenre(
        liveGenreNetworkState: MutableLiveData<NetworkState<GenreResponse>>
    ): Single<Response<GenreResponse>> {
        return genreAPI.requestGenre().networkCommunication()
    }

}