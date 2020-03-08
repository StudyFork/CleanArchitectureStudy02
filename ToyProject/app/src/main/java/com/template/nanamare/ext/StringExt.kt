package com.template.nanamare.ext

import com.google.gson.Gson
import com.template.nanamare.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

inline fun String.extractNumber(): String {
    return replace(Regex("[^\\d]"), "")
}

inline fun String.extractCapEnglish(): String {
    return replace(Regex("[^A-Za-z\\s]"), "")
}

inline fun String.extractCapEnglishNumber(): String {
    return replace(Regex("[^A-Za-z0-9\\s]"), "")
}

inline fun String.extractEngAddress(): String {
    return replace(Regex("[^A-Za-z0-9 \\-,.\\s/]"), "")
}

inline fun String.extractCapEngName(): String {
    return replace(Regex("[^A-Za-z0-9 \\-_,.'\\s]"), "")
}

inline fun String.masking(): String {
    return replace(Regex("[^\\s]"), "◼").replace(Regex("[ ]"), "")
}

inline fun String.replaceCustomRexExpression(rex: String): String {
    return replace(Regex("[^$rex]"), "")
}

inline fun String.extractCustomRexExpression(rex: String): List<Int> {
    val wrongs = mutableListOf<Int>()
    forEachIndexed { index, item ->
        if(Regex("[^$rex]").matches(item.toString())) {
            wrongs.add(index)
        }
    }
    return wrongs
}


inline fun String.needUpdate(): Boolean {
    val origin = BuildConfig.VERSION_NAME.split(".").map { if (it.isEmpty()) 0 else it.toInt() }
    val remote = this.split(".").map { if (it.isEmpty()) 0 else it.toInt() }

    return when {
        remote[0] > origin[0] -> true
        remote[0] < origin[0] -> false
        else -> when {
            remote[1] > origin[1] -> true
            remote[1] < origin[1] -> false
            else -> remote[2] > origin[2]
        }
    }
}

inline fun String.parseDateFormat(): Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)

inline fun <reified T> String.fromJson() = Gson().fromJson<T>(this)