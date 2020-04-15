package com.egiwon.moviesearch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    protected val mutableShowErrorTextResId = MutableLiveData<Int>()
    val showErrorTextResId: LiveData<Int> get() = mutableShowErrorTextResId

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    open fun onClick(model: Any) = Unit

}