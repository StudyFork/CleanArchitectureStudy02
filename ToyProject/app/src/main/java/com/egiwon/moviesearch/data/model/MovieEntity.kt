package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieEntity(
    val movieId: Int = 0,
    val title: String = "",
    val posterPath: String = ""
) : BaseIdentifier() {
    override val id: Any
        get() = movieId

}

