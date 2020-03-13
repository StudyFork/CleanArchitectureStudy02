package yunji.cleanarchitecturestudy02.model.source

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import yunji.cleanarchitecturestudy02.model.repository.MovieRepository
import yunji.cleanarchitecturestudy02.model.response.Movie
import yunji.cleanarchitecturestudy02.model.response.MovieListResponse

/*
 * Created by yunji on 12/03/2020
 */
class MoviePageDataSource private constructor(
    private val repository: MovieRepository,
    private val onPagingStart: () -> Unit,
    private val onPagingSuccess: (response: MovieListResponse) -> Unit,
    private val onPagingFailed: (errMsg: String) -> Unit
) : PageKeyedDataSource<Int, Movie>() {

    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGING_UNIT = 1
        private const val PAGE_SIZE = 20
        private val TAG = MoviePageDataSource::class.simpleName

        val moviePageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()
    }

    class Factory(
        private val repository: MovieRepository,
        private val onPagingStart: () -> Unit,
        private val onPagingSuccess: (response: MovieListResponse) -> Unit,
        private val onPagingFailed: (errMsg: String) -> Unit
    ) : DataSource.Factory<Int, Movie>() {

        override fun create(): DataSource<Int, Movie> =
            MoviePageDataSource(repository, onPagingStart, onPagingSuccess, onPagingFailed)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        run(onPagingStart)

        repository.getPopularMovieList(
            FIRST_PAGE,
            success = {
                callback.onResult(it.movies, null, FIRST_PAGE + PAGING_UNIT)
                onPagingSuccess(it)
            }, failed = {
                onPagingFailed(it)
                Log.e(TAG, it)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        repository.getPopularMovieList(params.key,
            success = {
                callback.onResult(it.movies, params.key + PAGE_SIZE)
                onPagingSuccess(it)
            }, failed = {
                onPagingFailed(it)
                Log.e(TAG, it)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // do nothing
    }
}