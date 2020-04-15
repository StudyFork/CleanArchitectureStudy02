package com.egiwon.moviesearch.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieDetailEntity
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.data.model.MovieTrailerEntity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<List<MovieEntity>>

    fun getPagingPopularMovies(
        compositeDisposable: CompositeDisposable,
        onFailure: (Throwable) -> Unit
    ): LiveData<PagedList<MovieEntity>>

    fun getMovieDetailInfo(movieId: Int): Single<MovieDetailEntity>

    fun getMovieTrailerInfo(movieId: Int): Single<MovieTrailerEntity>
}