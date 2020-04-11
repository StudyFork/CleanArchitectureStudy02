package com.god.taeiim.movieapp

import android.app.Application
import com.god.taeiim.movieapp.di.dataSourceModule
import com.god.taeiim.movieapp.di.networkModule
import com.god.taeiim.movieapp.di.viewModelModule
import com.god.taeiim.movieapp.ext.setupKoin

class MovieAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin(this, viewModelModule, networkModule, dataSourceModule)
    }

}