package com.egiwon.moviesearch.ui.vo

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieViewObject(
    override val id: Int,
    val title: String,
    val population: Double,
    val posterPath: String,
    val overView: String
) : BaseIdentifier()