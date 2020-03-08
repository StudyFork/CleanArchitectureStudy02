package com.template.nanamare.data.repository

import com.template.nanamare.data.enum.RequestMovieApiType
import com.template.nanamare.data.source.MovieRemoteDataSource
import com.template.nanamare.data.source.impl.MovieDataSourceImpl
import com.template.nanamare.network.response.BaseErrorResponse
import com.template.nanamare.network.response.MovieResponse

class MovieRepository(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieDataSourceImpl {

    override fun requestMovies(
        requestMovieApiType: RequestMovieApiType,
        query: String,
        page: Int,
        success: (movieResponse: MovieResponse) -> Unit,
        failed: (errorResponse: BaseErrorResponse) -> Unit
    ) {
        movieRemoteDataSource.requestMovies(
            requestMovieApiType,
            query,
            page,
            success,
            failed
        )
    }

}