package com.example.movieapplication.presenter.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.domain.GetPopularMovieUseCase
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.MovieItem
import com.example.movieapplication.utils.Event
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : BaseViewModel() {

    private var isBottomLoading = false
    private var totalPages = 0
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

    private val _toastLiveData = MutableLiveData<Event<String>>()
    val toastLiveData: LiveData<Event<String>> get() = _toastLiveData

    fun loadMovie() {

        showLoading()

        viewModelScope.launch {

            val result = getPopularMovieUseCase.get(page = 1)
            when (result) {
                is ResultWrapper.Success -> {
                    totalPages = result.value.totalPages
                    _movies.value = result.value.movies
                    hideError()
                }
                is ResultWrapper.HttpException -> {
                    showError(result.error)
                }
                is ResultWrapper.NetworkError -> {
                    showError(result.error)
                }
            }

            hideLoading()
        }
    }

    fun addMovie() {

        if (!isBottomLoading) {

            if (totalPages <= page) {
                _toastLiveData.value = Event("Last Page")
                return
            }

            showBottomLoading()

            page++

            isBottomLoading = true

            viewModelScope.launch {

                val result = getPopularMovieUseCase.get(page)
                when (result) {
                    is ResultWrapper.Success -> {
                        _movies.value = result.value.movies
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

