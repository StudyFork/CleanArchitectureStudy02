package com.egiwon.moviesearch.data.source.remote

import com.egiwon.moviesearch.data.source.remote.response.MovieCreditsResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieDetailResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieTrailerResponse
import com.egiwon.moviesearch.data.source.remote.service.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override fun getPopularMovies(page: Int): Single<MovieResponse> =
        movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())

    override fun getMovieDetailInfo(id: Int): Single<MovieDetailResponse> =
        movieService.getMovieDetails(id)
            .subscribeOn(Schedulers.io())

    override fun getMovieCredits(id: Int): Single<MovieCreditsResponse> =
        movieService.getMovieCredits(id)
            .subscribeOn(Schedulers.io())

    override fun getMovieTrailerInfo(id: Int): Single<MovieTrailerResponse> =
        movieService.getMovieTrailers(id)
            .subscribeOn(Schedulers.io())
}