package com.egiwon.moviesearch.data.source.page

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSource
import com.egiwon.moviesearch.data.source.remote.response.mapToMovieEntities
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val remoteDataSource: MovieRemoteDataSource,
    private val onSuccess: (List<MovieEntity>) -> Unit,
    private val onFailure: (Throwable) -> Unit
) : DataSource.Factory<Int, MovieEntity>() {

    private var totalPage = 1

    override fun create(): DataSource<Int, MovieEntity> {
        return MoviePagingDataSource(
            compositeDisposable,
            remoteDataSource
        )
    }

    inner class MoviePagingDataSource(
        private val compositeDisposable: CompositeDisposable,
        private val remoteDataSource: MovieRemoteDataSource
    ) : PageKeyedDataSource<Int, MovieEntity>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieEntity>) {
            remoteDataSource.getPopularMovies(START_PAGE)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        totalPage = it.totalPages
                        val resultList = it.mapToMovieEntities()
                        callback.onResult(resultList, 0, START_PAGE + 1)
                        onSuccess(resultList)
                    },
                    onError = {
                        onFailure(it)
                    }
                )
                .addTo(compositeDisposable)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) {

            if (params.key < totalPage) {
                remoteDataSource.getPopularMovies(START_PAGE)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                        onSuccess = {
                            totalPage = it.totalPages
                            val resultList = it.mapToMovieEntities()
                            callback.onResult(resultList, params.key + 1)
                            onSuccess(resultList)
                        },
                        onError = {
                            onFailure(it)
                        }
                    )
                    .addTo(compositeDisposable)
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) = Unit

    }

    companion object {
        private const val START_PAGE = 1
        private const val PAGE_SIZE = 20

        val moviePageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()
    }

}