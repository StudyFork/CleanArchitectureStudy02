package com.egiwon.moviesearch

import android.app.Application
import com.egiwon.moviesearch.di.dataSourceModule
import com.egiwon.moviesearch.di.networkModule
import com.egiwon.moviesearch.di.remoteDataSourceModule
import com.egiwon.moviesearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class MovieSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
            androidContext(this@MovieSearchApplication)
            modules(
                viewModelModule,
                dataSourceModule,
                remoteDataSourceModule,
                networkModule
            )
        }
    }
}