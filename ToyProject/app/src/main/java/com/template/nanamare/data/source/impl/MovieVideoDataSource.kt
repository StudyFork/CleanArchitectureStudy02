package com.template.nanamare.data.source.impl

import com.template.nanamare.network.response.MovieVideoResponse
import io.reactivex.Single
import retrofit2.Response

interface MovieVideoDataSource {
    fun getMovieVideos(movieId: Int) : Single<Response<MovieVideoResponse>>
}