package com.example.toyproject.data.repository

import com.example.toyproject.data.entity.SearchMovieResponse
import com.example.toyproject.data.remote.MovieRemoteDataSource
import com.example.toyproject.data.remote.MovieRemoteDataSourceImpl

object MovieRepositoryImpl : MovieRepository {
    val movieRemoteDataSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl()
    override fun getMovieData(
        query: String,
        success: (SearchMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) = movieRemoteDataSource.getMovieData(query, success, fail)


}