package com.mhuman.coroutine.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mhuman.coroutine.BuildConfig
import com.mhuman.coroutine.network.api.MovieApi
import com.mhuman.coroutine.network.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PopularDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, Result>(),
    KoinComponent {
    private var totalPage = 0
    private val movieApi: MovieApi by inject()

    val networkErrors: MutableLiveData<String> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        scope.launch {
            val response =
                movieApi.getPopularMovieList(BuildConfig.THE_MOVIE_API_KEY, page = FIRST_PAGE)
            when {
                response.isSuccessful ->
                    response.body()?.let {
                        totalPage = it.totalPages
                        callback.onResult(
                            it.results,
                            null,
                            FIRST_PAGE + 1
                        )
                    }
                else -> {
                    networkErrors.postValue(ERROR_MESSAGE_REQUEST_FAILED)
                }
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        if (params.key > totalPage) {
            networkErrors.postValue(LAST_PAGE_NOTI_MESSAGE)
            return
        }

        scope.launch {
            val response =
                movieApi.getPopularMovieList(BuildConfig.THE_MOVIE_API_KEY, page = params.key)

            when {
                response.isSuccessful ->
                    response.body()?.results?.let {
                        callback.onResult(
                            it,
                            params.key + 1
                        )
                    }
                else -> {
                    networkErrors.postValue(ERROR_MESSAGE_REQUEST_FAILED)
                }
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        //not-operation
    }

    companion object {
        const val FIRST_PAGE = 1
        const val ERROR_MESSAGE_REQUEST_FAILED = "데이터 요청을 실패하였습니다.\n네트워크 설정을 확인해주세요."
        const val ERROR_MESSAGE_EMPTY_DATA = "찾으시는 영화 데이터가 \n서버에 존재하지 않습니다."
        const val LAST_PAGE_NOTI_MESSAGE = "마지막 페이지입니다."
    }
}

