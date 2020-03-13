package yunji.cleanarchitecturestudy02.model.source

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import yunji.cleanarchitecturestudy02.model.response.MovieListResponse
import yunji.cleanarchitecturestudy02.network.MovieApi

/*
 * Created by yunji on 10/03/2020
 */
class MovieRemoteDataSource(
    private val movieApi: MovieApi
) : MovieDataSource {
    private val tag = MovieRemoteDataSource::class.simpleName

    override fun getPopularMovieList(
        page: Int,
        success: (movieListResponse: MovieListResponse) -> Unit,
        failed: (errMsg: String) -> Unit
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = movieApi.getPopularMovies(page).awaitResponse()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(it) }
                    } else {
                        failed(response.message())
                        Log.e(tag, response.body().toString())
                    }
                }
            } catch (e: Exception) {
                failed(e.message ?: "getPopularMovieList error")
                e.printStackTrace()
            }
        }
    }
}