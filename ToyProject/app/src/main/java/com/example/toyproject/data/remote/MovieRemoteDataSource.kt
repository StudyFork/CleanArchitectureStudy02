package com.example.toyproject.data.remote

import com.example.toyproject.data.entity.SearchMovieResponse

interface MovieRemoteDataSource {
    fun getMovieData(
        query: String,
        success: (SearchMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}