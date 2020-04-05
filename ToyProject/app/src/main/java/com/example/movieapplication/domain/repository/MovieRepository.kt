package com.example.movieapplication.domain.repository

import com.example.movieapplication.domain.model.MovieEntity

interface MovieRepository {

    suspend fun getPopularMovie(page: Int = 1): MovieEntity
}