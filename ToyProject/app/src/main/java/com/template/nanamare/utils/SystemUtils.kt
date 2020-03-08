package com.template.nanamare.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import java.util.*

class SystemUtils(val context: Context) {
    fun updateLanguage(language: Language, newContext: Context? = null): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(language.language, newContext)
        } else updateResourcesLegacy(language.language, newContext)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(language: String?, newContext: Context? = null): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)

        return (newContext ?: context).createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(language: String?, newContext: Context? = null): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = (newContext ?: context).resources

        val configuration = resources.configuration
        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return (newContext ?: context)
    }

}