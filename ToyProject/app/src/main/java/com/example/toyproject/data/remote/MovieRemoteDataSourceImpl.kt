package com.example.toyproject.data.remote

import com.example.toyproject.data.entity.SearchMovieResponse
import com.example.toyproject.network.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl() : MovieRemoteDataSource {
    val APP_KEY = "e29f73abf94a892a99c8df777d038279"

    override fun getMovieData(
        query: String,
        success: (SearchMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        retrofitService()
            .requestSearchMovie(APP_KEY, "space", 1)
            .enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(
                    call: Call<SearchMovieResponse>,
                    response: Response<SearchMovieResponse>
                ) {
                    response.body()?.let { success(it) }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    fail(t)
                }

            })
    }

}