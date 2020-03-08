package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.GenreDataSourceImpl
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse

class MainViewModel(private val genreDataSourceImpl: GenreDataSourceImpl) : BaseViewModel() {

    val liveGenreNetworkState = MutableLiveData<NetworkState<GenreResponse>>().apply {
        value = NetworkState.init()
    }

    val liveGenre = MutableLiveData<List<GenreResponse.Genre>>()

    fun requestMovieGenre() {
        genreDataSourceImpl.requestGenre(liveGenreNetworkState)
    }

}