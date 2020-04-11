package com.god.taeiim.movieapp.ext

import android.content.Context
import com.god.taeiim.movieapp.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module

fun setupKoin(
    context: Context,
    vararg module: Module
) {
    startKoin {
        logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
        androidContext(context)
        modules(*module)
    }
}
