package com.egiwon.moviesearch.data.source.remote.service

import com.egiwon.moviesearch.data.source.remote.response.MovieCreditsResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieDetailResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieTrailerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "ko"
    ): Single<MovieResponse>

    @GET("3/movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "ko"
    ): Single<MovieDetailResponse>

    @GET("3/movie/{movieId}/credits")
    fun getMovieCredits(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "ko"
    ): Single<MovieCreditsResponse>

    @GET("3/movie/{movieId}/videos")
    fun getMovieTrailers(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "ko"
    ): Single<MovieTrailerResponse>
}