package com.template.nanamare.data.source

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.template.nanamare.data.source.impl.GenreDataSourceImpl
import com.template.nanamare.ext.converterErrorBody
import com.template.nanamare.ext.networkCommunication
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.api.GenreAPI
import com.template.nanamare.network.response.GenreResponse

@Suppress("UnstableApiUsage")
@SuppressLint("CheckResult")
class GenreRemoteDataSource(private val genreAPI: GenreAPI) : GenreDataSourceImpl {

    override fun requestGenre(
        liveGenreNetworkState: MutableLiveData<NetworkState<GenreResponse>>
    ) {
        genreAPI.requestGenre().networkCommunication()
            .doOnSubscribe {
                liveGenreNetworkState.value = NetworkState.loading()
            }
            .doOnTerminate {
                liveGenreNetworkState.value = NetworkState.init()
            }
            .subscribe({
                when (it.isSuccessful) {
                    true -> {
                        it.body()?.let {
                            liveGenreNetworkState.value = NetworkState.success(it)
                        } ?: run {
                            liveGenreNetworkState.value =
                                NetworkState.error(Throwable("empty body"))
                        }
                    }
                    false -> {
                        it.errorBody()?.let {
                            liveGenreNetworkState.value =
                                NetworkState.serverError(converterErrorBody(it.string()))
                        } ?: run {
                            liveGenreNetworkState.value =
                                NetworkState.error(Throwable("empty errorBody"))
                        }
                    }
                }
            }, {
                liveGenreNetworkState.value = NetworkState.error(it)
            })
    }

}