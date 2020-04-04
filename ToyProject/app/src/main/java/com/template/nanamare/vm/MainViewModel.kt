package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.BaseResponse
import com.template.nanamare.network.response.GenreResponse
import io.reactivex.Single
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class MainViewModel(private val genreDataSource: GenreDataSource) : BaseViewModel() {

    val liveGenreNetworkState = MutableLiveData<NetworkState<GenreResponse>>().apply {
        value = NetworkState.init()
    }

    fun requestMovieGenre() {
        compositeDisposable.add(
            getDisposable(
                genreDataSource.requestGenre() as Single<Response<BaseResponse>>,
                liveGenreNetworkState as MutableLiveData<NetworkState<BaseResponse>>
            )
        )
    }

}