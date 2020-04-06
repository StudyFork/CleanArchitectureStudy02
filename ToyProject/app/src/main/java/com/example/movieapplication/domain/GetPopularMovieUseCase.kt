package com.example.movieapplication.domain

import com.example.movieapplication.AppProvider
import com.example.movieapplication.R
import com.example.movieapplication.data.model.ErrorResponse
import com.example.movieapplication.domain.model.mapToPresenter
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.Movie
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class GetPopularMovieUseCase(
    private val movieRepository: MovieRepository,
    private val appProvider: AppProvider
) {

    suspend fun get(page: Int): ResultWrapper<Movie> {
        return try {
            val imageWidthRatio = 2f
            val imageHeightRatio = 3f

            val deviceWidth = appProvider.getDeviceWidth()
            val imageMargin = appProvider.getDimens(R.dimen.item_movie_horizontal_margin)

            val imageWidth = (deviceWidth - (imageMargin * 3)) / 2
            val imageHeight = imageWidth * (imageHeightRatio / imageWidthRatio)

            val response = movieRepository.getPopularMovie(page)

            val movie = Movie(
                totalPages = response.totalPages,
                movies = response.movies.map {
                    it.mapToPresenter(imageWidth.toInt(), imageHeight.toInt())
                }
            )
            ResultWrapper.Success(movie)
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