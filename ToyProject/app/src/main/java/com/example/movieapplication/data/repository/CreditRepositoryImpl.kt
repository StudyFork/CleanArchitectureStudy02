package com.example.movieapplication.data.repository

import com.example.movieapplication.data.source.CreditApi
import com.example.movieapplication.domain.model.CreditEntity
import com.example.movieapplication.domain.repository.CreditRepository

class CreditRepositoryImpl(
    private val creditApi: CreditApi
) : CreditRepository {

    override suspend fun getCredits(movieId: Int): CreditEntity {
        val response = creditApi.getCredits(movieId)

        return CreditEntity(
            cast = response.cast.map {
                CreditEntity.CastEntity(
                    profilePath = it.profilePath ?: "",
                    name = it.name
                )
            }
        )
    }
}