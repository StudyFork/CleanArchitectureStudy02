package com.egiwon.moviesearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.data.MovieRepository
import com.egiwon.moviesearch.data.model.mapToMovieViewObject
import com.egiwon.moviesearch.ui.vo.MovieViewObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _resultMovieList = MutableLiveData<List<MovieViewObject>>()
    val resultMovieList: LiveData<List<MovieViewObject>> get() = _resultMovieList

    fun loadPopularMovies() {
        Single.create<List<MovieViewObject>> { emitter ->
            repository.getPagingPopularMovies(
                compositeDisposable = compositeDisposable,
                onSuccess = { resultMovieList ->
                    emitter.onSuccess(resultMovieList.map { it.mapToMovieViewObject() })
                }, onFailure = {
                mutableShowErrorTextResId.value = R.string.error_load_fail_text
            }
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { resultList ->
                _resultMovieList.value = resultList
            }
            .addTo(compositeDisposable)

    }
}