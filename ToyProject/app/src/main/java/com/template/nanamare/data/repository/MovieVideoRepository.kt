package com.template.nanamare.data.repository

import com.template.nanamare.data.source.MovieVideoRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.MovieVideoDataSource
import com.template.nanamare.network.response.MovieVideoResponse
import io.reactivex.Single
import retrofit2.Response

class MovieVideoRepository(private val movieVideoRemoteDataSourceImpl: MovieVideoRemoteDataSourceImpl) :
    MovieVideoDataSource {

    override fun getMovieVideos(movieId: Int): Single<Response<MovieVideoResponse>> {
        return movieVideoRemoteDataSourceImpl.getMovieVideos(movieId)
    }

}