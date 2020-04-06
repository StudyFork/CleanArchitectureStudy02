package com.example.movieapplication.di

import com.example.movieapplication.domain.GetMovieDetailWithCreditUseCase
import com.example.movieapplication.domain.GetPopularMovieUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetPopularMovieUseCase(get(), get())
    }

    factory {
        GetMovieDetailWithCreditUseCase(get(), get(), get())
    }

}