package com.example.movieapplication.presenter.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.domain.GetMovieDetailWithCreditUseCase
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.MovieDetail
import com.example.movieapplication.utils.Event
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    val movieId: Int,
    private val getMovieDetailWithCreditUseCase: GetMovieDetailWithCreditUseCase
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _toastLiveData = MutableLiveData<Event<String>>()
    val toastLiveData: LiveData<Event<String>> get() = _toastLiveData

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail

    private val _actors = Transformations.map(_movieDetail) { it.actors }
    val actors: LiveData<List<MovieDetail.Actor>> get() = _actors

    fun loadMoveDetail() {

        showLoading()

        viewModelScope.launch {

            val result = getMovieDetailWithCreditUseCase.get(movieId)
            when (result) {
                is ResultWrapper.Success -> {
                    _movieDetail.value = result.value
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
}

