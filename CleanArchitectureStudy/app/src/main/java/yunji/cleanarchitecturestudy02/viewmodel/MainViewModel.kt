package yunji.cleanarchitecturestudy02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import yunji.cleanarchitecturestudy02.model.repository.MovieRepository
import yunji.cleanarchitecturestudy02.model.response.Movie
import yunji.cleanarchitecturestudy02.model.source.MoviePageDataSource
import java.util.concurrent.Executors

/*
 * Created by yunji on 10/03/2020
 */
class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isExist = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()
    private val executor = Executors.newSingleThreadExecutor()
    lateinit var pagingMovieList: LiveData<PagedList<Movie>>

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isExist: LiveData<Boolean>
        get() = _isExist

    val msgText: LiveData<String>
        get() = _msgText

    fun initMovieData() {
        val pageDataSourceFactory = MoviePageDataSource.Factory(repository,
            onPagingStart = { _isLoading.postValue(true) },
            onPagingSuccess = {
                _isLoading.postValue(false)
                _isExist.postValue(pagingMovieList.value?.size!! > 0)
            },
            onPagingFailed = {
                _msgText.postValue(it)
                _isLoading.postValue(false)
                _isExist.postValue(false)
            })

        pagingMovieList = LivePagedListBuilder(pageDataSourceFactory, MoviePageDataSource.moviePageConfig)
            .setFetchExecutor(executor)
            .build()
    }
}