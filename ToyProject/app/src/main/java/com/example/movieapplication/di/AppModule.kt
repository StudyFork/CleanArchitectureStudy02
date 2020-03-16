package com.example.movieapplication.di

import com.example.movieapplication.AppProvider
import com.example.movieapplication.AppProviderImpl
import com.example.movieapplication.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("debug")) { BuildConfig.DEBUG }

    single<AppProvider> {
        AppProviderImpl(androidApplication())
    }
}