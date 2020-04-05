package com.example.movieapplication.data.repository

import com.example.movieapplication.data.model.ResultWrapper
import com.example.movieapplication.presenter.model.Movie

interface MovieRepository {

    suspend fun getPopularMovie(page: Int = 1): ResultWrapper<Movie>
}