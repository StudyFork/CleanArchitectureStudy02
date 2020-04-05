package com.example.movieapplication.data.repository

import com.example.movieapplication.data.model.mapToDomain
import com.example.movieapplication.data.source.MovieApi
import com.example.movieapplication.domain.model.MovieEntity
import com.example.movieapplication.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovie(page: Int): MovieEntity {
        val response = movieApi.getPopular(page)

        return MovieEntity(
            totalPages = response.totalPages,
            movies = response.results.map { it.mapToDomain() }
        )
    }
}