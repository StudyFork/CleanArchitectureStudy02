package yunji.cleanarchitecturestudy02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yunji.cleanarchitecturestudy02.model.repository.MovieRepository
import yunji.cleanarchitecturestudy02.model.response.Movie

/*
 * Created by yunji on 10/03/2020
 */
class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()
    private var page = 1
    private var totalPages = 1

    val movieList: LiveData<List<Movie>>
        get() = _movieList

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val msgText: LiveData<String>
        get() = _msgText

    init {
        _movieList.value = listOf()
    }

    fun getPopularMovies() {
        if (page > totalPages) {
            return
        }

        _isLoading.value = true
        repository.getPopularMovieList(page, success = {
            totalPages = it.totalPages
            _movieList.value = _movieList.value?.plus(it.movies)
            _isLoading.value = false
        }, failed = {
            _msgText.value = it
            _isLoading.value = false
        })
    }

    fun getNextPopularMovies() {
        page += 1
        getPopularMovies()
    }
}