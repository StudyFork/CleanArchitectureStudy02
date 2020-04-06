package com.example.movieapplication.di

import com.example.movieapplication.presenter.movie.MovieViewModel
import com.example.movieapplication.presenter.moviedetail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { MovieViewModel(get()) }

    viewModel { (movieId: Int) -> MovieDetailViewModel(movieId, get()) }

}