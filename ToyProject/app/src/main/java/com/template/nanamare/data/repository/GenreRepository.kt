package com.template.nanamare.data.repository

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.data.source.GenreRemoteDataSource
import com.template.nanamare.data.source.impl.GenreDataSourceImpl
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse

class GenreRepository(private val genreRemoteDataSource: GenreRemoteDataSource) :
    GenreDataSourceImpl {

    override fun requestGenre(
        liveGenreNetworkState: MutableLiveData<NetworkState<GenreResponse>>
    ) {
        genreRemoteDataSource.requestGenre(liveGenreNetworkState)
    }

}