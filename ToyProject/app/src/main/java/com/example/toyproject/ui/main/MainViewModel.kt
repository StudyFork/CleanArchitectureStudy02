package com.example.toyproject.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyproject.data.entity.SearchMovieData
import com.example.toyproject.data.repository.MovieRepositoryImpl

class MainViewModel() : ViewModel() {
    val TAG = this.javaClass.name
    val movieData = MutableLiveData<List<SearchMovieData>>()
    val query = MutableLiveData<String>()

    fun searchMovie() {
        MovieRepositoryImpl.getMovieData(query.toString(), success = {
            movieData.value = it.searchMovieResults
        }, fail = {
            Log.e(TAG, it.message)
        })
    }
}