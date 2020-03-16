package com.egiwon.moviesearch.di

import com.egiwon.moviesearch.data.MovieRepository
import com.egiwon.moviesearch.data.MovieRepositoryImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}