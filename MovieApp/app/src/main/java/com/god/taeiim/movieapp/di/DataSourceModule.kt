package com.god.taeiim.movieapp.di

import com.god.taeiim.movieapp.data.repository.MovieRepository
import com.god.taeiim.movieapp.data.repository.MovieRepositoryImpl
import com.god.taeiim.movieapp.data.source.remote.MovieRemoteDataSource
import com.god.taeiim.movieapp.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
