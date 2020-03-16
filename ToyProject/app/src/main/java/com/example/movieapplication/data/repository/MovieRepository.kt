package com.example.movieapplication.data.repository

import com.example.movieapplication.presenter.model.MovieItem

interface MovieRepository {

    suspend fun get(page: Int = 1): List<MovieItem>
}