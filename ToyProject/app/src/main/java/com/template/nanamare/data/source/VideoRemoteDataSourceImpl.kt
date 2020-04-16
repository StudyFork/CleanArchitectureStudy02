package com.template.nanamare.data.source

import com.template.nanamare.data.source.impl.VideoDataSource
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.movie.MovieAPI
import com.template.nanamare.network.response.VideoResponse
import io.reactivex.Single
import retrofit2.Response

class VideoRemoteDataSourceImpl(private val movieAPI: MovieAPI) : VideoDataSource {
    override fun getMovieVideos(movieId: Int): Single<Response<VideoResponse>> {
        return movieAPI.requestMovieVideos(movieId).networkDispatchToMain()
    }
}