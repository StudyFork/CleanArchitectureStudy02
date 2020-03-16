package com.example.movieapplication.di

import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.data.repository.MovieRepositoryImpl
import com.example.movieapplication.data.source.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }

    single { get<Retrofit>().create(MovieApi::class.java) }
}