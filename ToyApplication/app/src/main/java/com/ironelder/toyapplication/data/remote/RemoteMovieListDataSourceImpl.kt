package com.ironelder.toyapplication.data.remote

import com.ironelder.toyapplication.data.api.NetworkServiceApi
import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import com.ironelder.toyapplication.data.service.NetworkService
import kotlinx.coroutines.Deferred

class RemoteMovieListDataSourceImpl : RemoteMovieListDataSource {
    override suspend fun getSearchMovie(
        query: String,
        page: Int?,
        adult: Boolean?
    ):MovieListModel {
        return NetworkServiceApi.movieServiceApi.getSearchMovie(query = query)
    }
}