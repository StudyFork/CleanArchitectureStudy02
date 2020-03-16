package com.ironelder.toyapplication

import android.app.Application
import com.ironelder.toyapplication.BuildConfig
import timber.log.Timber

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}