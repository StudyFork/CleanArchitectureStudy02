package com.example.movieapplication.domain.repository

import com.example.movieapplication.domain.model.CreditEntity

interface CreditRepository {

    suspend fun getCredits(movieId: Int): CreditEntity
}