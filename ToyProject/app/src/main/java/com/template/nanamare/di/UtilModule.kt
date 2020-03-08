package com.template.nanamare.di

import com.template.nanamare.utils.*
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DI_PREF_UTILS = "DI_PREF_UTILS"
const val DI_SYSTEM_UTILS = "DI_SYSTEM_UTILS"

val utilModule = module {
    single(named(DI_PREF_UTILS)) { PrefUtils(androidApplication()) }
    single(named(DI_SYSTEM_UTILS)) {
        SystemUtils(
            androidApplication()
        )
    }
    factory { UiUtils(get(named(DI_LANGUAGE_CONTEXT)), get()) }
    factory { StringResUtils(get(named(DI_LANGUAGE_CONTEXT)), get()) }
    single { AssetFileReader(androidApplication()) }
}