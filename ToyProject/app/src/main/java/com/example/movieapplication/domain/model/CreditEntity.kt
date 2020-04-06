package com.example.movieapplication.domain.model

data class CreditEntity(
    val cast: List<CastEntity>
) {
    data class CastEntity(
        val profilePath: String,
        val name: String
    )
}