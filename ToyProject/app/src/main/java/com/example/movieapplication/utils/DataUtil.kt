package com.example.movieapplication.utils

import java.text.DecimalFormat

object DataUtil {

    fun getDate(releaseData: String): String {
        val date = releaseData.split("-")

        return if (!date.isNullOrEmpty() && date.size == 3) {
            val year = "${date[0]}년"
            val month = "${date[1]}월"
            val day = "${date[2]}일"

            "$year $month $day"
        } else {
            releaseData
        }
    }

    fun getPercent(percent: Double): String {
        return "$percent%"
    }

    fun getCommaCount(count: Int): String {
        return DecimalFormat("###,###").format(count)
    }
}