package com.egiwon.moviesearch.di

import com.egiwon.moviesearch.ui.MainViewModel
import com.egiwon.moviesearch.ui.detail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { MovieDetailViewModel(get()) }
}