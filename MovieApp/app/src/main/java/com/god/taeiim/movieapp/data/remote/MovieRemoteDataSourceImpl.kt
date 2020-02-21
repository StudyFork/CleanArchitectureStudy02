package com.god.taeiim.movieapp.data.remote

import com.god.taeiim.movieapp.data.source.MovieDataSource
import com.god.taeiim.movieapp.api.model.MoviePopular
import com.god.taeiim.movieapp.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MovieRemoteDataSourceImpl:
    MovieDataSource {

    override suspend fun getPopularMovies(page: Int): Result<MoviePopular> {
//        return GlobalScope.async { Dispatchers.IO } {
//
//        }.await()
    }

}