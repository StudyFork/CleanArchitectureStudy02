package com.god.taeiim.movieapp.data.repository

import com.god.taeiim.movieapp.data.model.Movie


interface MovieRepository {

    suspend fun getPopularMovies(page: Int): List<Movie>

}