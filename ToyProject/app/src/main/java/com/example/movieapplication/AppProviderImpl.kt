package com.example.movieapplication

import android.app.Application
import android.content.Context.WINDOW_SERVICE
import android.util.DisplayMetrics
import android.view.WindowManager


class AppProviderImpl(private val application: Application) : AppProvider {

    override fun getDeviceWidth(): Int {
        val dm = DisplayMetrics()
        val windowManager = application.getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    override fun getDimens(dimensId: Int): Float {
        return application.resources.getDimension(dimensId)
    }
}