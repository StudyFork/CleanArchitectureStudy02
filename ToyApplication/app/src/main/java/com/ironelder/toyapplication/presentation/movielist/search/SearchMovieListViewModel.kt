package com.ironelder.toyapplication.presentation.movielist.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import com.ironelder.toyapplication.data.remote.RemoteMovieListDataSourceImpl
import com.ironelder.toyapplication.data.repository.MovieRepository
import com.ironelder.toyapplication.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

class SearchMovieListViewModel : ViewModel() {
    private val repository: MovieRepository = MovieRepositoryImpl(RemoteMovieListDataSourceImpl())
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, t ->
        t.printStackTrace()
    }
    val searchResultData : LiveData<MovieListModel> = liveData(Dispatchers.IO + coroutineExceptionHandler) {
        val retrievedData = repository.getSearchMovie("Car")
        emit(retrievedData)
    }
}
