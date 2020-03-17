package com.egiwon.moviesearch.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.data.source.page.MovieDataSourceFactory
import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSource
import com.egiwon.moviesearch.data.source.remote.response.mapToMovieEntities
import com.egiwon.moviesearch.ext.onFailureRequestMovie
import com.egiwon.moviesearch.ext.onSuccessRequestMovie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies(page: Int): Single<List<MovieEntity>> =
        movieRemoteDataSource.getPopularMovies(page)
            .map { movieResponses -> movieResponses.mapToMovieEntities() }

    override fun getPagingPopularMovies(
        compositeDisposable: CompositeDisposable,
        onSuccess: onSuccessRequestMovie,
        onFailure: onFailureRequestMovie
    ): LiveData<PagedList<MovieEntity>> {
        val movieDataSourceFactory =
            MovieDataSourceFactory(compositeDisposable, movieRemoteDataSource, onSuccess, onFailure)
        return LivePagedListBuilder(movieDataSourceFactory, MovieDataSourceFactory.moviePageConfig)
            .build()
    }

}