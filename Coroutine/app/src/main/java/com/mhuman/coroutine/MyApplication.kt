package com.mhuman.coroutine

import android.app.Application
import com.mhuman.coroutine.di.getNetworkModule
import com.mhuman.coroutine.di.repositoryModule
import com.mhuman.coroutine.di.viewModelModule
import com.mhuman.coroutine.network.api.MovieApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    getNetworkModule(MovieApi.API_BASE_URL),
                    repositoryModule
                )
            )
        }
    }
}