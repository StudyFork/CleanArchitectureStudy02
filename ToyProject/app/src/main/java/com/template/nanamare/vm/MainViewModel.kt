package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.ext.converterErrorBody
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse

class MainViewModel(private val genreDataSource: GenreDataSource) : BaseViewModel() {

    val liveGenreNetworkState = MutableLiveData<NetworkState<GenreResponse>>().apply {
        value = NetworkState.init()
    }

    fun requestMovieGenre() {
        compositeDisposable.add(genreDataSource.requestGenre(liveGenreNetworkState)
            .doOnSubscribe { liveGenreNetworkState.value = NetworkState.loading() }
            .doOnTerminate { liveGenreNetworkState.value = NetworkState.init() }
            .subscribe({ response ->
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            liveGenreNetworkState.value = NetworkState.success(it)
                        } ?: run {
                            liveGenreNetworkState.value = NetworkState.error(Throwable("empty body"))
                        }
                    }
                    false -> {
                        response.errorBody()?.let {
                            liveGenreNetworkState.value = NetworkState.serverError(converterErrorBody(it.string()))
                        } ?: run {
                            liveGenreNetworkState.value = NetworkState.error(Throwable("empty errorBody"))
                        }
                    }
                }
            }, {
                liveGenreNetworkState.value = NetworkState.error(it)
            })
        )
    }

}