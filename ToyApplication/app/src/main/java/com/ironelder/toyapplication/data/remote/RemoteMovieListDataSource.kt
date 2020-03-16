package com.ironelder.toyapplication.data.remote

import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import kotlinx.coroutines.Deferred

interface RemoteMovieListDataSource {
    suspend fun getSearchMovie(
        query: String,
        page: Int? = 1,
        adult: Boolean? = false
    ): MovieListModel
}