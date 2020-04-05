package com.example.movieapplication.di

import com.example.movieapplication.domain.GetPopularMovieUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        GetPopularMovieUseCase(get(), get())
    }

}