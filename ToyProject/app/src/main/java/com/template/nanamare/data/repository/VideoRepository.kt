package com.template.nanamare.data.repository

import com.template.nanamare.data.source.VideoRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.VideoDataSource
import com.template.nanamare.network.response.VideoResponse
import io.reactivex.Single
import retrofit2.Response

class VideoRepository(private val videoRemoteDataSourceImpl: VideoRemoteDataSourceImpl) :
    VideoDataSource {

    override fun getMovieVideos(movieId: Int): Single<Response<VideoResponse>> {
        return videoRemoteDataSourceImpl.getMovieVideos(movieId)
    }

}