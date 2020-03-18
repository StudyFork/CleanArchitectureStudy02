package com.egiwon.moviesearch.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.data.MovieRepository
import com.egiwon.moviesearch.data.model.MovieEntity

class MainViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    lateinit var resultMovieList: LiveData<PagedList<MovieEntity>>

    fun loadPopularMovies() {
        resultMovieList = repository.getPagingPopularMovies(
            compositeDisposable = compositeDisposable,
            onFailure = {
                mutableShowErrorTextResId.value = R.string.error_load_fail_text
            }
        )
    }
}