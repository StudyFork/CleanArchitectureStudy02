package com.example.movieapplication.data.repository

import com.example.movieapplication.AppProvider
import com.example.movieapplication.R
import com.example.movieapplication.constant.Constant
import com.example.movieapplication.data.model.mapToPresenter
import com.example.movieapplication.data.source.remote.MovieApi
import com.example.movieapplication.presenter.model.MovieItem

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val appProvider: AppProvider
) : MovieRepository {

    private val imageWidthRatio = 2f
    private val imageHeightRatio = 3f

    private val deviceWidth = appProvider.getDeviceWidth()
    private val imageMargin = appProvider.getDimens(R.dimen.item_movie_horizontal_margin)

    private val imageWidth = (deviceWidth - (imageMargin * 3)) / 2
    private val imageHeight = imageWidth * (imageHeightRatio / imageWidthRatio)

    override suspend fun get(page: Int): List<MovieItem> {
        val response = movieApi.getPopular(Constant.apiKey, page)
        return response.results.map { it.mapToPresenter(imageWidth.toInt(), imageHeight.toInt()) }
    }
}