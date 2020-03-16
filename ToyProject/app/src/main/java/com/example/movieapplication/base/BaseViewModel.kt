package com.example.movieapplication.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel() {

    /*private val coroutineExceptionHanlder = CoroutineExceptionHandler { _, exception ->

    }*/

    protected val ioDispatchers = Dispatchers.IO
    protected val uiDispatchers = Dispatchers.Main

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}