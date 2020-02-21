package com.god.taeiim.movieapp.data.source

import com.god.taeiim.movieapp.api.model.MoviePopular
import com.god.taeiim.movieapp.data.Result

interface MovieRepository {

    suspend fun getPopularMovies(page: Int): Result<MoviePopular>

}