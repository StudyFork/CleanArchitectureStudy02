package com.egiwon.moviesearch.ext

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

fun View.onThrottleButtonClickListener(
    compositeDisposable: CompositeDisposable,
    action: () -> Unit
) {
    clicks().throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe { action() }
        .addTo(compositeDisposable)
}

