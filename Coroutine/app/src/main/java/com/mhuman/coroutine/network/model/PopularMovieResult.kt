package com.mhuman.coroutine.network.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PopularMovieResult(
    val data: LiveData<PagedList<Result>>,
    val netWorkErrors: LiveData<String>?
)