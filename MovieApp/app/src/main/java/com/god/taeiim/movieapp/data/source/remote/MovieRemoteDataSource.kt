package com.god.taeiim.movieapp.data.source.remote

import com.god.taeiim.movieapp.data.model.Movie

interface MovieRemoteDataSource {

    suspend fun getPopularMovies(page: Int): List<Movie>

}