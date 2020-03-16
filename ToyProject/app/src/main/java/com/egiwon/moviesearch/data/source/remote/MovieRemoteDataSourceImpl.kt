package com.egiwon.moviesearch.data.source.remote

import com.egiwon.moviesearch.data.source.remote.response.MovieResponse
import com.egiwon.moviesearch.data.source.remote.service.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override fun getPopularMovies(page: Int): Single<MovieResponse> =
        movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())

}