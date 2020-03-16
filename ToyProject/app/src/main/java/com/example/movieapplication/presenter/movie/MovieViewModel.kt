package com.example.movieapplication.presenter.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.presenter.model.MovieItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val movieRepo: MovieRepository) : BaseViewModel() {

    private var isBottomLoading = false
    private var page = 1

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _bottomLoading = MutableLiveData<Boolean>(false)
    val bottomLoading: LiveData<Boolean> get() = _bottomLoading

    private val _movies = MutableLiveData<List<MovieItem>>(mutableListOf())
    val movies: LiveData<List<MovieItem>> get() = _movies

    fun loadMovie() {

        showLoading()

        viewModelScope.launch(ioDispatchers) {
            try {
                val items = movieRepo.get(1)

                withContext(uiDispatchers) {
                    hideLoading()
                    _movies.value = items
                }
            } catch (e: Exception) {
                withContext(uiDispatchers) {
                    hideLoading()
                }
            }
        }
    }

    fun addMovie() {

        if (!isBottomLoading) {

            showBottomLoading()

            page++

            isBottomLoading = true

            viewModelScope.launch(ioDispatchers) {

                val items = movieRepo.get(page)

                withContext(uiDispatchers) {

                    hideBottomLoading()

                    isBottomLoading = false

                    _movies.value = items
                }
            }
        }
    }

    private fun showLoading() {
        _loading.postValue(true)
    }

    private fun hideLoading() {
        _loading.postValue(false)
    }

    private fun showBottomLoading() {
        _bottomLoading.postValue(true)
    }

    private fun hideBottomLoading() {
        _bottomLoading.postValue(false)
    }
}