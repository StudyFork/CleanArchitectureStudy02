package com.egiwon.moviesearch.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieEntity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<List<MovieEntity>>

    fun getPagingPopularMovies(
        compositeDisposable: CompositeDisposable,
        onSuccess: (response: List<MovieEntity>) -> Unit,
        onFailure: (Throwable) -> Unit
    ): LiveData<PagedList<MovieEntity>>
}