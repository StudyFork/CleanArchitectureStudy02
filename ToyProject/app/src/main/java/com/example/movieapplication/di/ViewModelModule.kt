package com.example.movieapplication.di

import com.example.movieapplication.presenter.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { MovieViewModel(get()) }
}