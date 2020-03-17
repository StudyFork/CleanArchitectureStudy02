package com.egiwon.moviesearch.ext

import com.egiwon.moviesearch.data.model.MovieEntity

typealias onSuccessRequestMovie = (response: List<MovieEntity>) -> Unit
typealias onFailureRequestMovie = (Throwable) -> Unit