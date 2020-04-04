package yunji.cleanarchitecturestudy02.model.source

import yunji.cleanarchitecturestudy02.model.response.MovieListResponse

/*
 * Created by yunji on 10/03/2020
 */
interface MovieDataSource {

    fun getPopularMovieList(
        page: Int,
        success: (movieListResponse: MovieListResponse) -> Unit,
        failed: (errMsg: String) -> Unit
    )
}