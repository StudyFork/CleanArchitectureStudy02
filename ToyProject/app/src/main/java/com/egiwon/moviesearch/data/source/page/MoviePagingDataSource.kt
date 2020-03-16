package com.egiwon.moviesearch.data.source.page

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieEntity

class MoviePagingDataSource : PageKeyedDataSource<Int, MovieEntity>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieEntity>
    ) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) {

    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGING_UNIT = 1
        private const val PAGE_SIZE = 20
        private val TAG = MoviePagingDataSource::class.simpleName

        val moviePageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()
    }
}