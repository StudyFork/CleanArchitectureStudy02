package com.egiwon.moviesearch.ui.model

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieViewObject(
    override val id: Int,
    val title: String,
    val posterPath: String
) : BaseIdentifier()