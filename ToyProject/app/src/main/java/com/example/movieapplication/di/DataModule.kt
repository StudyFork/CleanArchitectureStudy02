package com.example.movieapplication.di

import com.example.movieapplication.data.repository.MovieRepositoryImpl
import com.example.movieapplication.data.source.MovieApi
import com.example.movieapplication.domain.repository.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single { get<Retrofit>().create(MovieApi::class.java) }
}