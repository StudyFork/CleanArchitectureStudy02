package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse

class MainViewModel(private val genreDataSource: GenreDataSource) : BaseViewModel() {

    val liveGenreNetworkState = MutableLiveData<NetworkState<GenreResponse>>().apply {
        value = NetworkState.init()
    }

    fun requestMovieGenre() {
        genreDataSource.requestGenre(liveGenreNetworkState)
    }

}