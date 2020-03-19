package com.egiwon.moviesearch.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egiwon.moviesearch.data.model.MovieDetailEntity
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.data.source.paging.MovieDataSourceFactory
import com.egiwon.moviesearch.data.source.remote.MovieRemoteDataSource
import com.egiwon.moviesearch.data.source.remote.response.mapToMovieDetailEntity
import com.egiwon.moviesearch.data.source.remote.response.mapToMovieEntities
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

typealias onFailureRequestMovie = (Throwable) -> Unit

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies(page: Int): Single<List<MovieEntity>> =
        movieRemoteDataSource.getPopularMovies(page)
            .map { movieResponses -> movieResponses.mapToMovieEntities() }

    override fun getPagingPopularMovies(
        compositeDisposable: CompositeDisposable,
        onFailure: onFailureRequestMovie
    ): LiveData<PagedList<MovieEntity>> {

        val movieDataSourceFactory =
            MovieDataSourceFactory(compositeDisposable, movieRemoteDataSource, onFailure)

        return LivePagedListBuilder(movieDataSourceFactory, MovieDataSourceFactory.moviePageConfig)
            .setFetchExecutor {
                Completable
                    .fromRunnable(it)
                    .subscribeOn(Schedulers.single())
                    .subscribe()
                    .addTo(compositeDisposable)
            }
            .build()
    }

    override fun getMovieDetailInfo(movieId: Int): Single<MovieDetailEntity> =
        movieRemoteDataSource.getMovieDetailInfo(movieId)
            .map { movieDetailResponse -> movieDetailResponse.mapToMovieDetailEntity() }

}