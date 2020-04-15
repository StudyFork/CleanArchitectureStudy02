package com.egiwon.moviesearch.data.source.remote

import com.egiwon.moviesearch.data.source.remote.response.MovieCreditsResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieDetailResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieResponse
import com.egiwon.moviesearch.data.source.remote.response.MovieTrailerResponse
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getPopularMovies(page: Int): Single<MovieResponse>

    fun getMovieDetailInfo(id: Int): Single<MovieDetailResponse>

    fun getMovieCredits(id: Int): Single<MovieCreditsResponse>

    fun getMovieTrailerInfo(id: Int): Single<MovieTrailerResponse>
}