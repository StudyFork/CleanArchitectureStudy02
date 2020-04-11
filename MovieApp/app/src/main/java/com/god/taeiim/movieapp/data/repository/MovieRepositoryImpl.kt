package com.god.taeiim.movieapp.data.repository

import com.god.taeiim.movieapp.data.model.Movie
import com.god.taeiim.movieapp.data.source.remote.MovieRemoteDataSource


class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return movieRemoteDataSource.getPopularMovies(page)
    }

}