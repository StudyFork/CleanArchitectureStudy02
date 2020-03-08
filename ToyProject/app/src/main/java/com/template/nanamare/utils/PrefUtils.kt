package com.template.nanamare.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.securepreferences.SecurePreferences
import com.template.nanamare.ext.fromJson
import java.util.*

class PrefUtils(context: Context) {

    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    private val secureSharedPref = SecurePreferences(context)

    @SuppressLint("CommitPrefEdits")
    private fun getEdit() = sharedPref.edit()

    private fun getSecureEdit() = secureSharedPref.edit()

    companion object {
        const val PREF_KEY_COUNTRY_CONFIGURE = "PREF_KEY_COUNTRY_CONFIGURE"
        const val PREF_KEY_LANGUAGE = "PREF_KEY_LANGUAGE"
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val PREF_KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN"
        const val PREF_KEY_NOTIFICATION_SETTING = "PREF_KEY_NOTIFICATION_SETTING"
        const val PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO"
        const val PREF_ONE_CLICK_ACCOUNT = "PREF_ONE_CLICK_ACCOUNT"
        const val PREF_IS_FIRST_OPEN = "PREF_IS_FIRST_OPEN"
        const val PREF_NOTICE_POPUPS = "PREF_NOTICE_POPUPS"
        const val PREF_KEY_RATE_CONSIST_ISO_CURRENY = "PREF_KEY_RATE_CONSIST_ISO_CURRENY"
    }

    fun saveRateKey(rateKey: String) {
        getSecureEdit().putString(PREF_KEY_RATE_CONSIST_ISO_CURRENY, rateKey).apply()
    }

    fun loadRateKey(): String {
        return secureSharedPref.getString(PREF_KEY_RATE_CONSIST_ISO_CURRENY, "") ?: ""
    }

    fun saveLanguage(language: Language) {
        getEdit().putString(PREF_KEY_LANGUAGE, Gson().toJson(language)).apply()
    }

    fun saveLanguage(code: String) {
        saveLanguage(getCodeToLanguage(code))
    }

    fun loadLanguage(): Language {
        return Gson().fromJson<Language>(sharedPref.getString(PREF_KEY_LANGUAGE, null))
                ?: run { getCodeToLanguage(Locale.getDefault().language) }
    }

    private fun getCodeToLanguage(code: String) = when (code) {
        Language.KOREAN.language -> Language.KOREAN
        "fil", Language.TAGALOG.language -> Language.TAGALOG
        "th" -> Language.THAI
        "vi" -> Language.VIETNAM
        Locale.CHINESE.language -> Language.CHINESE
        else -> Language.ENGLISH
    }

    fun saveAccessToken(token: String) {
        getSecureEdit().putString(PREF_KEY_ACCESS_TOKEN, token).apply()
    }

    fun loadAccessToken(): String {
        return secureSharedPref.getString(PREF_KEY_ACCESS_TOKEN, "") ?: ""
    }

    fun saveRefreshToken(token: String) {
        getSecureEdit().putString(PREF_KEY_REFRESH_TOKEN, token).apply()
    }

    fun loadRefreshToken(): String {
        return secureSharedPref.getString(PREF_KEY_REFRESH_TOKEN, "") ?: ""
    }

    fun clearUserData() {
        saveAccessToken("")
        saveRefreshToken("")
        saveIsFirstTime(true)
    }

    fun saveIsFirstTime(first: Boolean) {
        getEdit().putBoolean(PREF_IS_FIRST_OPEN, first).apply()
    }

    fun getIsFirstTime(): Boolean {
        return sharedPref.getBoolean(PREF_IS_FIRST_OPEN, true)
    }

    fun saveNoticePopups(notices: Map<Int, Long>?) {
        getSecureEdit().putString(PREF_NOTICE_POPUPS, Gson().toJson(notices)).apply()
    }

    fun clearExpiredNoticePopups(): Map<Int, Long>? {
        try {
            val noticePopups = getNoticePopups()
            if (noticePopups == null || noticePopups.isEmpty()) return null
            val currentTime = System.currentTimeMillis()
            val filteredNoticePopups : Map<Int, Long>? = noticePopups.filterValues { it > currentTime }
            saveNoticePopups(filteredNoticePopups)
            return filteredNoticePopups
        } catch (e: Exception) {
            return null
        }
    }

    fun getNoticePopups(): Map<Int, Long>? {
        return try {
            Gson().fromJson<Map<Int, Long>>(secureSharedPref.getString(PREF_NOTICE_POPUPS, null))
        } catch (e: Exception) {
            null
        }
    }

    fun appendNoticePopups(newBlockedNotices: Map<Int, Long>?) {
        val noticePopups = getNoticePopups()
        val newNoticePopups = if (noticePopups == null) mutableMapOf<Int, Long>() else noticePopups.toMutableMap()
        for ((k, v) in newBlockedNotices!!) {
            newNoticePopups[k] = v
        }
        saveNoticePopups(newNoticePopups)
    }


}