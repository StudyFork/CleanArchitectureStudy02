package yunji.cleanarchitecturestudy02.model.repository

import yunji.cleanarchitecturestudy02.model.response.MovieListResponse
import yunji.cleanarchitecturestudy02.model.source.MovieDataSource
import yunji.cleanarchitecturestudy02.model.source.MovieRemoteDataSource
import yunji.cleanarchitecturestudy02.network.RetrofitBuilder

/*
 * Created by yunji on 10/03/2020
 */
object MovieRepository : MovieDataSource {
    private val movieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder.service)

    override fun getPopularMovieList(
        page: Int,
        success: (movieListResponse: MovieListResponse) -> Unit,
        failed: (errMsg: String) -> Unit
    ) {
        movieRemoteDataSource.getPopularMovieList(page, success, failed)
    }
}