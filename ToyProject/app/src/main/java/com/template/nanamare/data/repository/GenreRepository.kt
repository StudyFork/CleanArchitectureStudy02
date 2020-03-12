package com.template.nanamare.data.repository

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.data.source.GenreRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response

class GenreRepository(private val genreRemoteDataSourceImplImpl: GenreRemoteDataSourceImpl) :
    GenreDataSource {

    override fun requestGenre(
        liveGenreNetworkState: MutableLiveData<NetworkState<GenreResponse>>
    ): Single<Response<GenreResponse>> {
        return genreRemoteDataSourceImplImpl.requestGenre(liveGenreNetworkState)
    }

}