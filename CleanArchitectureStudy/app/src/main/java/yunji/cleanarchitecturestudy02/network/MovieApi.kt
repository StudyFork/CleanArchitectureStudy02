package yunji.cleanarchitecturestudy02.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import yunji.cleanarchitecturestudy02.DEFAULT_LANGUAGE
import yunji.cleanarchitecturestudy02.model.response.MovieDetailsResponse
import yunji.cleanarchitecturestudy02.model.response.MovieListResponse

/*
 * Created by yunji on 09/03/2020
 */
interface MovieApi {

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): Call<MovieListResponse>

    @GET("3/movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): Call<MovieDetailsResponse>
}