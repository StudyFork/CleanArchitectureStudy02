package com.example.toyproject.data.repository

import com.example.toyproject.data.entity.SearchMovieResponse

interface MovieRepository {
    fun getMovieData (
        query: String,
        success: (SearchMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}