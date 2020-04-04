package com.template.nanamare.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.nanamare.ext.converterErrorBody
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.BaseResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class BaseViewModel :
        ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun getDisposable(
        baseResponse: Single<Response<BaseResponse>>,
        liveState: MutableLiveData<NetworkState<BaseResponse>>
    ): Disposable {
        return baseResponse
            .doOnSubscribe { liveState.value = NetworkState.loading() }
            .doOnTerminate { liveState.value = NetworkState.init() }
            .subscribe({ response ->
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {liveState.value = NetworkState.success(it)
                        } ?: run {
                            liveState.value = NetworkState.error(Throwable("empty body"))
                        }
                    }
                    false -> {
                        response.errorBody()?.let {
                            liveState.value = NetworkState.serverError(converterErrorBody(it.string()))
                        } ?: run {
                            liveState.value = NetworkState.error(Throwable("empty errorBody"))
                        }
                    }
                }
            }, {
                liveState.value = NetworkState.error(it)
            })
    }
}