package yunji.cleanarchitecturestudy02.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import yunji.cleanarchitecturestudy02.model.response.Movie
import yunji.cleanarchitecturestudy02.model.response.MovieListResponse
import yunji.cleanarchitecturestudy02.model.source.MovieDataSource
import yunji.cleanarchitecturestudy02.model.source.MoviePageDataSource
import yunji.cleanarchitecturestudy02.model.source.MovieRemoteDataSource
import yunji.cleanarchitecturestudy02.network.RetrofitBuilder
import java.util.concurrent.Executors

/*
 * Created by yunji on 10/03/2020
 */
object MovieRepository : MovieDataSource {
    private val movieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder.service)
    private val executor = Executors.newSingleThreadExecutor()

    fun getMoviePagedList(
        onPagingStart: () -> Unit,
        onPagingSuccess: (response: MovieListResponse) -> Unit,
        onPagingFailed: (errMsg: String) -> Unit
    ): LiveData<PagedList<Movie>> {
        val pageDataSourceFactory = MoviePageDataSource.Factory(this, onPagingStart, onPagingSuccess, onPagingFailed)
        return LivePagedListBuilder(pageDataSourceFactory, MoviePageDataSource.moviePageConfig)
            .setFetchExecutor(executor)
            .build()
    }

    override fun getPopularMovieList(
        page: Int,
        success: (movieListResponse: MovieListResponse) -> Unit,
        failed: (errMsg: String) -> Unit
    ) {
        movieRemoteDataSource.getPopularMovieList(page, success, failed)
    }
}