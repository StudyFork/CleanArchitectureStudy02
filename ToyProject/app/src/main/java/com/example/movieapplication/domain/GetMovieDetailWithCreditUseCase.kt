package com.example.movieapplication.domain

import com.example.movieapplication.AppProvider
import com.example.movieapplication.data.model.ErrorResponse
import com.example.movieapplication.domain.repository.CreditRepository
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.MovieDetail
import com.example.movieapplication.utils.DataUtil
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class GetMovieDetailWithCreditUseCase(
    private val movieRepository: MovieRepository,
    private val creditRepository: CreditRepository,
    private val appProvider: AppProvider
) {

    suspend fun get(movieId: Int): ResultWrapper<MovieDetail> {
        return try {

            val movieInfo = movieRepository.getMovieDetail(movieId)
            val actors = creditRepository.getCredits(movieId).cast

            val movieDetail = MovieDetail(
                posterPath = movieInfo.posterPath,
                title = movieInfo.title,
                summary = movieInfo.overview,
                releaseDate = DataUtil.getDate(movieInfo.releaseDate),
                voteAverage = DataUtil.getPercent(movieInfo.voteAverage),
                voteCount = DataUtil.getCommaCount(movieInfo.voteCount),
                actors = actors.map {
                    MovieDetail.Actor(
                        profileUrl = it.profilePath,
                        name = it.name
                    )
                }
            )

            ResultWrapper.Success(movieDetail)
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