package com.template.nanamare.data.source.impl

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse

interface GenreDataSource {

    fun requestGenre(
        liveGenreNetworkState: MutableLiveData<NetworkState<GenreResponse>>
    )

}