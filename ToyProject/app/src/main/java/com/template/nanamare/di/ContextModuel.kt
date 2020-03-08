package com.template.nanamare.di

import com.template.nanamare.utils.PrefUtils
import com.template.nanamare.utils.SystemUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DI_LANGUAGE_CONTEXT = "DI_LANGUAGE_CONTEXT"

val contextModule = module {
    factory(named(DI_LANGUAGE_CONTEXT)) {
        (get(named(DI_SYSTEM_UTILS)) as SystemUtils).updateLanguage(
            (get(named(DI_PREF_UTILS)) as PrefUtils).loadLanguage()
            , androidApplication()
        )
    }
}