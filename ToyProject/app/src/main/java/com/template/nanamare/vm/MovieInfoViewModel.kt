package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.MovieCreditDataSource
import com.template.nanamare.data.source.impl.MovieDetailDataSource
import com.template.nanamare.data.source.impl.MovieVideoDataSource
import com.template.nanamare.data.vo.Actor
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.*
import io.reactivex.Single
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class MovieInfoViewModel(
    private val movieVideoDataSource: MovieVideoDataSource,
    private val movieCreditDataSource: MovieCreditDataSource,
    private val movieDetailDataSource: MovieDetailDataSource
) : BaseViewModel() {

    val liveMovieVideoState = MutableLiveData<NetworkState<MovieVideoResponse>>().apply {
        value = NetworkState.init()
    }

    val liveMovieCreditState = MutableLiveData<NetworkState<MovieCreditResponse>>().apply {
        value = NetworkState.init()
    }

    val liveMovieDetailResponse = MutableLiveData<NetworkState<MovieDetailResponse>>().apply {
        value = NetworkState.init()
    }

    var movie: MovieResponse.Result? = null

    val liveHasSample = MutableLiveData<Boolean>().apply {
        value = false
    }

    val liveLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    val liveActor = MutableLiveData<List<Actor>>()

    fun onSeeSampleClick() {
        movie?.let {
            compositeDisposable.add(
                disposable(
                    movieVideoDataSource.getMovieVideos(it.id) as Single<Response<BaseResponse>>,
                    liveMovieVideoState as MutableLiveData<NetworkState<BaseResponse>>
                )
            )
        }
    }

    fun getMovieCredit(movieId: Int) {
        compositeDisposable.add(
            disposable(
                movieCreditDataSource.getMovieCredit(movieId) as Single<Response<BaseResponse>>,
                liveMovieCreditState as MutableLiveData<NetworkState<BaseResponse>>
            )
        )
    }

    fun getMovieDetail(creditId: Int) {
        compositeDisposable.add(
            disposable(
                movieDetailDataSource.getMovieDetail(creditId) as Single<Response<BaseResponse>>,
                liveMovieCreditState as MutableLiveData<NetworkState<BaseResponse>>
            )
        )
    }

}