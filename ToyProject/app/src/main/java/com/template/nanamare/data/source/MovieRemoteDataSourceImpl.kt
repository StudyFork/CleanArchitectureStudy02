package com.template.nanamare.data.source

import android.annotation.SuppressLint
import com.template.nanamare.data.enum.RequestMovieApiType
import com.template.nanamare.data.source.impl.MovieDataSource
import com.template.nanamare.ext.converterErrorBody
import com.template.nanamare.ext.networkDispatchToMain
import com.template.nanamare.network.api.MovieAPI
import com.template.nanamare.network.response.BaseErrorResponse
import com.template.nanamare.network.response.MovieResponse

@SuppressLint("CheckResult")
class MovieRemoteDataSourceImpl(private val movieAPI: MovieAPI) : MovieDataSource {

    override fun requestMovies(
        requestMovieApiType: RequestMovieApiType,
        query: String,
        page: Int,
        success: (movieResponse: MovieResponse) -> Unit,
        failed: (errorResponse: BaseErrorResponse) -> Unit
    ) {

        when (requestMovieApiType) {
            RequestMovieApiType.DISCOVER -> movieAPI.requestMovies(withGenres = query)
            RequestMovieApiType.SEARCH -> movieAPI.searchMovies(query = query)
        }.networkDispatchToMain()
            .subscribe({ response ->
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            success(it)
                        } ?: run {
                            failed(BaseErrorResponse(-1, "", false))
                        }
                    }
                    false -> {
                        response.errorBody()?.let {
                            failed(converterErrorBody(it.string()))
                        } ?: run {
                            failed(BaseErrorResponse(-1, "", false))
                        }
                    }
                }
            }, {
                failed(BaseErrorResponse(-1, "", false))
            })
    }


}