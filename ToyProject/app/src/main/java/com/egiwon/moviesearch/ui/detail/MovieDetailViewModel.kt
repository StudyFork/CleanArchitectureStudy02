package com.egiwon.moviesearch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.data.MovieRepository
import com.egiwon.moviesearch.data.model.mapToMovieDetailViewObject
import com.egiwon.moviesearch.ui.model.MovieDetailViewObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MovieDetailViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _movieDetailInfo = MutableLiveData<MovieDetailViewObject>()
    val movieDetailInfo: LiveData<MovieDetailViewObject> get() = _movieDetailInfo

    fun getMovieDetailInfo(movieId: Int) {
        repository.getMovieDetailInfo(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                _movieDetailInfo.value = it.mapToMovieDetailViewObject()
            }
            .addTo(compositeDisposable)
    }
}