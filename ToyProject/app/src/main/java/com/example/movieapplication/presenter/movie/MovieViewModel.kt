package com.example.movieapplication.presenter.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.data.model.ResultWrapper
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.presenter.model.MovieItem
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepo: MovieRepository) : BaseViewModel() {

    private var isBottomLoading = false
    private var page = 1

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> get() = _errorMessage
    val moviesVisibility = Transformations.map(_errorMessage) { it.isEmpty() }

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _bottomLoading = MutableLiveData<Boolean>(false)
    val bottomLoading: LiveData<Boolean> get() = _bottomLoading

    private val _movies = MutableLiveData<List<MovieItem>>(mutableListOf())
    val movies: LiveData<List<MovieItem>> get() = _movies

    fun loadMovie() {

        showLoading()

        viewModelScope.launch {
            hideLoading()

            val result = movieRepo.get(page = 1)
            when (result) {
                is ResultWrapper.Success -> {
                    _movies.value = result.value
                    hideError()
                }
                is ResultWrapper.HttpException -> {
                    showError(result.error)
                }
                is ResultWrapper.NetworkError -> {
                    showError(result.error)
                }
            }
        }
    }

    fun addMovie() {

        if (!isBottomLoading) {

            showBottomLoading()

            page++

            isBottomLoading = true

            viewModelScope.launch {

                val result = movieRepo.get(page)
                when (result) {
                    is ResultWrapper.Success -> {
                        _movies.value = result.value
                        hideError()
                    }
                    is ResultWrapper.HttpException -> {
                        showError(result.error)
                    }
                    is ResultWrapper.NetworkError -> {
                        showError(result.error)
                    }
                }

                //result 보다 아래에 위치해야 합니다.
                hideBottomLoading()

                isBottomLoading = false
            }
        }
    }

    private fun showError(error: String) {
        _errorMessage.value = error
    }

    private fun hideError() {
        _errorMessage.value = ""
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

