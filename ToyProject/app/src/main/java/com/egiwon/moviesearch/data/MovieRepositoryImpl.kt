package com.egiwon.moviesearch.data

import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSource
import com.egiwon.moviesearch.data.source.remote.response.mapToMovieEntities
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies(page: Int): Single<List<MovieEntity>> =
        movieRemoteDataSource.getPopularMovies(page)
            .map { movieResponses -> movieResponses.mapToMovieEntities() }
}