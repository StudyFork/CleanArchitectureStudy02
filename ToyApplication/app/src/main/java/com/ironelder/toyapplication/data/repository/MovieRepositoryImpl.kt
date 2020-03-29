package com.ironelder.toyapplication.data.repository

import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import com.ironelder.toyapplication.data.remote.RemoteMovieListDataSource
import com.ironelder.toyapplication.data.remote.RemoteMovieListDataSourceImpl
import kotlinx.coroutines.Deferred

class MovieRepositoryImpl(private val remoteMovieListDataSource: RemoteMovieListDataSource) :
    MovieRepository {
    override suspend fun getSearchMovie(
        query: String,
        page: Int?,
        adult: Boolean?
    ): MovieListModel {
        return remoteMovieListDataSource.getSearchMovie(query = query)
    }
}