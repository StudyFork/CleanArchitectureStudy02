package com.template.nanamare.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.template.nanamare.base.navigator.BaseNavigator
import com.template.nanamare.base.ui.BaseViewModel
import com.template.nanamare.data.enum.RequestMovieApiType
import com.template.nanamare.data.source.MoviePagingDataSourceFactory
import com.template.nanamare.data.source.impl.MovieDataSourceImpl
import com.template.nanamare.network.response.MovieResponse

class MovieCategoryViewModel(
    private val movieDataSourceImpl: MovieDataSourceImpl,
    private val baseNavigator: BaseNavigator
) :
    BaseViewModel() {

    var liveMovies =
        MutableLiveData<LiveData<PagedList<MovieResponse.Result>>>()

    val liveIsResultZero = MutableLiveData<Boolean>()

    fun requestDiscoverMovies(id: Int, requestMovieApiType: RequestMovieApiType) {
        baseNavigator.showLoadingPopup()
        liveMovies.value = LivePagedListBuilder(MoviePagingDataSourceFactory(
            movieDataSourceImpl,
            requestMovieApiType = requestMovieApiType,
            query = id.toString(),
            success = {
                baseNavigator.hideLoadingPopup()
            },
            failed = {
                baseNavigator.hideLoadingPopup()
            }
        ), 50).setBoundaryCallback(object :
            PagedList.BoundaryCallback<MovieResponse.Result>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                liveIsResultZero.value = true
            }
        }).build()
    }

    fun searchMovies(query: String, requestMovieApiType: RequestMovieApiType) {
        baseNavigator.showLoadingPopup()
        liveMovies.value = LivePagedListBuilder(MoviePagingDataSourceFactory(
            movieDataSourceImpl,
            requestMovieApiType = requestMovieApiType,
            query = query,
            success = {
                baseNavigator.hideLoadingPopup()
            },
            failed = {
                baseNavigator.hideLoadingPopup()
            }
        ), 50).setBoundaryCallback(object :
            PagedList.BoundaryCallback<MovieResponse.Result>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                liveIsResultZero.value = true
            }
        }).build()
    }

}