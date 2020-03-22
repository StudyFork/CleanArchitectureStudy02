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
                MovieRepositoryImpl.getMovieData(query.value!!, success = {
                    movieData.value = it.searchMovieResults
                }, fail = {
                    Log.e(TAG, it.message)
                })
            }
            showLoading()
        }
        hideLoading()
    }

    private fun showLoading() {
        isLoading.value = true
    }

    private fun hideLoading() {
        isLoading.value = false
    }
}