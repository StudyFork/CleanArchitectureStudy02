package com.example.movieapplication.data.repository

import com.example.movieapplication.AppProvider
import com.example.movieapplication.R
import com.example.movieapplication.constant.Constant
import com.example.movieapplication.data.model.ErrorResponse
import com.example.movieapplication.data.model.ResultWrapper
import com.example.movieapplication.data.model.mapToPresenter
import com.example.movieapplication.data.source.remote.MovieApi
import com.example.movieapplication.presenter.model.MovieItem
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

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

    override suspend fun get(page: Int): ResultWrapper<List<MovieItem>> {
        return try {
            val response = movieApi.getPopular(Constant.apiKey, page)
            val movieItems = response.results.map {
                it.mapToPresenter(imageWidth.toInt(), imageHeight.toInt())
            }
            ResultWrapper.Success(movieItems)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errors = convertErrorBody(throwable).errors
                    ResultWrapper.HttpException(code, errors.first())
                }
                else -> {
                    ResultWrapper.HttpException(-1, unknownError)
                }
            }
        }
    }

    private val unknownError = "Unknown Error"
    private val gson = Gson()

    private fun convertErrorBody(throwable: HttpException): ErrorResponse {
        return try {
            throwable.response()?.errorBody()?.let { errorBody ->
                gson.fromJson(errorBody.string(), ErrorResponse::class.java)
            } ?: ErrorResponse(listOf(unknownError))
        } catch (exception: Exception) {
            ErrorResponse(listOf(unknownError))
        }
    }
}