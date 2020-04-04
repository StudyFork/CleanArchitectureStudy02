package com.template.nanamare.base.ui

import androidx.lifecycle.ViewModel
import com.template.nanamare.utils.Logger
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel :
        ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}