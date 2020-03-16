package com.egiwon.moviesearch.data

import com.egiwon.moviesearch.data.model.MovieEntity
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<List<MovieEntity>>
}