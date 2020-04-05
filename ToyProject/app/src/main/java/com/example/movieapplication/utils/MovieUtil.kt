package com.example.movieapplication.utils

import android.text.TextUtils

object MovieUtil {

    fun getPoster(posterPath: String?): String {
        return if (TextUtils.isEmpty(posterPath)) {
            ""
        } else {
            "https://image.tmdb.org/t/p/w500/${posterPath}" // 500 x 750
        }
    }
}