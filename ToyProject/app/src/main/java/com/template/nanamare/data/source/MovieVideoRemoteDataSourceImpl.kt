package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.MovieVideoDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.MovieVideoAPI
import com.template.nanamare.network.response.MovieVideoResponse
import io.reactivex.Single
import retrofit2.Response

class MovieVideoRemoteDataSourceImpl(private val movieVideoAPI: MovieVideoAPI) : MovieVideoDataSource {

    override fun getMovieVideos(movieId: Int): Single<Response<MovieVideoResponse>> {
        return movieVideoAPI.requestMovieVideos(movieId).networkDispatchToMain()
    }

}