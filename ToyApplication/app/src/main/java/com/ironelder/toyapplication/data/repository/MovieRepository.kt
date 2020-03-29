package com.ironelder.toyapplication.data.repository

import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import kotlinx.coroutines.Deferred

interface MovieRepository {
    suspend fun getSearchMovie(
        query: String,
        page: Int? = 1,
        adult: Boolean? = false
    ): MovieListModel
}