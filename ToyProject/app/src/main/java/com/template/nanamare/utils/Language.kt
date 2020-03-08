package com.template.nanamare.utils

import java.util.*

enum class Language(val language: String) {
    KOREAN(Locale.KOREAN.language),
    ENGLISH(Locale.ENGLISH.language),
    TAGALOG("tl"),
    THAI("th"),
    VIETNAM("vi"),
    CHINESE(Locale.CHINESE.language),
    NEPALI("ne"),
    SINHALA("si")
}