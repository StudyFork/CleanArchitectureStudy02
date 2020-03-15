package com.mhuman.coroutine.data.remote

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mhuman.coroutine.network.model.PopularMovieResult
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PopularPagingRepository : KoinComponent {

    private val pageListConfig: PagedList.Config by lazy {
        PagedList.Config.Builder()
            .setPageSize(500)
            .setInitialLoadSizeHint(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(true)
            .build()
    }

    private val executor: Executor = Executors.newFixedThreadPool(5)

    fun requestPopularMovieList(viewModelScope: CoroutineScope): PopularMovieResult {
        val dataSourceFactory = PopularPagingFactory(viewModelScope)

        val data = LivePagedListBuilder(dataSourceFactory, pageListConfig)
            .setFetchExecutor(executor)
            .build()

        val networkErrors = Transformations.switchMap(
            dataSourceFactory.liveMoviePagingData
        ) { dataSource -> dataSource.networkErrors }

        return PopularMovieResult(data, networkErrors)
    }
}