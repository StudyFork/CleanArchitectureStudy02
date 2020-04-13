package com.egiwon.moviesearch.ui.model

import com.egiwon.moviesearch.base.BaseIdentifier

data class MovieCastViewObject(
    val castId: Int = 0,
    val name: String = "",
    val profilePath: String? = ""
) : BaseIdentifier() {
    override val id: Any
        get() = castId
}