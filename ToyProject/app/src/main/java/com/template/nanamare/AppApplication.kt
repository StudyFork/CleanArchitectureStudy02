package com.template.nanamare

import android.app.Application
import com.google.android.play.core.missingsplits.MissingSplitsManagerFactory
import com.google.firebase.FirebaseApp
import com.template.nanamare.base.ui.BaseExceptionHandler
import com.template.nanamare.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
            // Skip app initialization.
            return
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppApplication)
            fragmentFactory()
            modules(
                listOf(
                    fragmentModule,
                    networkModule,
                    dataSourceModel,
                    viewModelModule,
                    apiModule
                )
            )
        }

        registerActivityLifecycleCallbacks(AppActivityLifecycleCallback)

        setDefaultHandler()
    }

    private fun setDefaultHandler() {
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { _, _ ->
            // Crashlytics에서 기본 handler를 호출하기 때문에 이중으로 호출되는것을 막기위해 빈 handler로 설정
        }
        Thread.setDefaultUncaughtExceptionHandler(
            BaseExceptionHandler(this, defaultExceptionHandler)
        )
    }

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(AppActivityLifecycleCallback)
        super.onTerminate()
    }

    companion object {
        @JvmStatic
        val TAG_APPLICATION: String = AppApplication::class.java.simpleName

}