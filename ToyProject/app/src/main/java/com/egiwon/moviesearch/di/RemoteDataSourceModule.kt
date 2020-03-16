package com.egiwon.moviesearch.di

import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSource
import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}