package com.egiwon.moviesearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.data.MovieRepository
import com.egiwon.moviesearch.data.model.MovieEntity

class MainViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    lateinit var resultMovieList: LiveData<PagedList<MovieEntity>>

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> get() = _movie

    fun loadPopularMovies() {
        resultMovieList = repository.getPagingPopularMovies(
            compositeDisposable = compositeDisposable,
            onFailure = {
                mutableShowErrorTextResId.value = R.string.error_load_fail_text
            }
        )
    }

    override fun onClick(model: Any) {
        if (model is MovieEntity) {
            _movie.value = model
        }
    }
}