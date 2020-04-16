package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.source.impl.CreditDataSource
import com.template.nanamare.data.source.impl.DetailDataSource
import com.template.nanamare.data.source.impl.VideoDataSource
import com.template.nanamare.data.vo.Actor
import com.template.nanamare.ext.upCasting
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.CreditResponse
import com.template.nanamare.network.response.DetailResponse
import com.template.nanamare.network.response.MovieResponse
import com.template.nanamare.network.response.VideoResponse

@Suppress("UNCHECKED_CAST")
class MovieInfoViewModel(
    private val videoDataSource: VideoDataSource,
    private val creditDataSource: CreditDataSource,
    private val movieDetailDataSource: DetailDataSource
) : BaseViewModel() {

    val liveMovieVideoState = MutableLiveData<NetworkState<VideoResponse>>().apply {
        value = NetworkState.init()
    }

    val liveMovieCreditState = MutableLiveData<NetworkState<CreditResponse>>().apply {
        value = NetworkState.init()
    }

    val liveMovieDetailResponse = MutableLiveData<NetworkState<DetailResponse>>().apply {
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
                getDisposable(
                    videoDataSource.getMovieVideos(it.id).upCasting(),
                    liveMovieVideoState.upCasting()
                )
            )
        }
    }

    fun getMovieCredit(movieId: Int) {
        compositeDisposable.add(
            getDisposable(
                creditDataSource.getMovieCredit(movieId).upCasting(),
                liveMovieCreditState.upCasting()
            )
        )
    }

    fun getMovieDetail(creditId: Int) {
        compositeDisposable.add(
            getDisposable(
                movieDetailDataSource.getMovieDetail(creditId).upCasting(),
                liveMovieCreditState.upCasting()
            )
        )
    }

}