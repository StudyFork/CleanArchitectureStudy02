package com.mhuman.coroutine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.mhuman.coroutine.base.BaseViewModel
import com.mhuman.coroutine.data.remote.PopularPagingRepository
import com.mhuman.coroutine.enum.Genre
import com.mhuman.coroutine.network.model.PopularMovieResult
import com.mhuman.coroutine.network.model.Result

class MainViewModel(
    private val popularRepository: PopularPagingRepository
) : BaseViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    private var requestResult: LiveData<PopularMovieResult> = Transformations.map(queryLiveData) {
        popularRepository.requestPopularMovieList(viewModelScope)
    }

    var livePopularMovieList: LiveData<PagedList<Result>> = Transformations
        .switchMap(requestResult)
        {
            it.data
        }

    val liveNetworkErrors: LiveData<String> = Transformations
        .switchMap(requestResult)
        {
            it.netWorkErrors
        }

    fun getPopularData() {
        queryLiveData.value = Genre.POPULAR.name
    }

    fun getPagedListLiveData(): LiveData<PagedList<Result>> {
        return livePopularMovieList
    }
}
