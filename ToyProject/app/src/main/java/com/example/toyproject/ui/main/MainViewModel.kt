package com.example.toyproject.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyproject.data.entity.SearchMovieData
import com.example.toyproject.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel() : ViewModel() {
    private val TAG = this.javaClass.name
    val movieData = MutableLiveData<List<SearchMovieData>>()
    val query = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun searchMovie() {
        runBlocking {
            launch {
                MovieRepositoryImpl.getMovieData(query.toString(), success = {
                    movieData.value = it.searchMovieResults
                }, fail = {
                    Log.e(TAG, it.message)
                })
            }
            isLoading.value = true
        }
        isLoading.value = false
    }
}