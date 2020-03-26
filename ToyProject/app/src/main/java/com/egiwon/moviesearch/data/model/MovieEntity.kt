package com.egiwon.moviesearch.data.model

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieEntity(
    override val id: Int = 0,
    val title: String = "",
    val posterPath: String = ""
) : BaseIdentifier()

