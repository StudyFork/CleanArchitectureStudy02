package com.god.taeiim.movieapp.data.source.remote

import com.god.taeiim.movieapp.data.model.Movie
import com.god.taeiim.movieapp.data.source.MovieApi
import retrofit2.HttpException

class MovieRemoteDataSourceImpl(private val movieApi: MovieApi) :
    MovieRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return try {
            movieApi.getMoviePopular(page).movies
        } catch (e: HttpException) {
            listOf()
        }
    }

}