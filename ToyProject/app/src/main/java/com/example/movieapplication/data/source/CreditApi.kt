package com.example.movieapplication.data.source

import com.example.movieapplication.data.model.CreditResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditApi {

    @GET("{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int
    ): CreditResponse
}