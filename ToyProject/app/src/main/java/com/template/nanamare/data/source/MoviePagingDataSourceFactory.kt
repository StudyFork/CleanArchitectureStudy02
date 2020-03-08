package com.template.nanamare.data.source

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.template.nanamare.data.enum.RequestMovieApiType
import com.template.nanamare.data.source.impl.MovieDataSourceImpl
import com.template.nanamare.network.response.BaseErrorResponse
import com.template.nanamare.network.response.MovieResponse

class MoviePagingDataSourceFactory(
    private val movieDataSourceImpl: MovieDataSourceImpl,
    private val requestMovieApiType: RequestMovieApiType,
    private val query: String,
    private val success: () -> Unit,
    private val failed: (BaseErrorResponse) -> Unit
) : DataSource.Factory<Int, MovieResponse.Result>() {

    override fun create(): DataSource<Int, MovieResponse.Result> {
        return MovieDataSource()
    }

    private var totalPage = 1

    inner class MovieDataSource : PageKeyedDataSource<Int, MovieResponse.Result>() {

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, MovieResponse.Result>
        ) {
            movieDataSourceImpl.requestMovies(requestMovieApiType, query, 0
                , success = {
                    totalPage = it.totalPages
                    callback.onResult(it.results, 0, it.page + 1)
                    success.invoke()
                }, failed = {
                    failed.invoke(it)
                })
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, MovieResponse.Result>
        ) {

            if (totalPage < params.key) {
                movieDataSourceImpl.requestMovies(requestMovieApiType, query,
                    params.key,
                    success = {
                        callback.onResult(it.results, it.page + 1)
                    },
                    failed = {
                        failed.invoke(it)
                    })
            }
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, MovieResponse.Result>
        ) {

        }


    }
}
