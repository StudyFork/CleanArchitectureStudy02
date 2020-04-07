package com.egiwon.moviesearch.ui.model

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieCastViewObject(
    override val id: Int = 0,
    val name: String = "",
    val profilePath: String? = ""
) : BaseIdentifier()