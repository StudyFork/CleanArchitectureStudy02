package com.god.taeiim.movieapp.data.source

import com.god.taeiim.movieapp.api.model.MoviePopular
import com.god.taeiim.movieapp.data.Result
import com.god.taeiim.movieapp.data.Result.Success


class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Result<MoviePopular> {
        val remoteMovies = movieRemoteDataSource.getPopularMovies(page)

        if (remoteMovies is Success) {


        } else if (remoteMovies is Result.Error) {
            throw remoteMovies.exception
        }

        return remoteMovies
    }

}