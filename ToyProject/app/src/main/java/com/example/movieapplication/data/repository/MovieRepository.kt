package com.example.movieapplication.data.repository

import com.example.movieapplication.data.model.ResultWrapper
import com.example.movieapplication.presenter.model.MovieItem

interface MovieRepository {

    suspend fun getPopularMovie(page: Int = 1): ResultWrapper<List<MovieItem>>
}