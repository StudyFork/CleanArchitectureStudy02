package com.egiwon.moviesearch.data.source.remote

import com.egiwon.moviesearch.data.source.remote.response.MovieResponse
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getPopularMovies(page: Int): Single<MovieResponse>
}