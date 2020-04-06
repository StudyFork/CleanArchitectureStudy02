package com.mhuman.coroutine.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mhuman.coroutine.network.model.Result
import kotlinx.coroutines.CoroutineScope

class PopularPagingFactory(viewModelScope: CoroutineScope) : DataSource.Factory<Int, Result>() {

    val liveMoviePagingData: MutableLiveData<PopularDataSource> = MutableLiveData()
    private val scope = viewModelScope
    private lateinit var dataSource: PopularDataSource

    override fun create(): DataSource<Int, Result> {
        dataSource = PopularDataSource(scope)
        liveMoviePagingData.postValue(dataSource)
        return dataSource
    }
}