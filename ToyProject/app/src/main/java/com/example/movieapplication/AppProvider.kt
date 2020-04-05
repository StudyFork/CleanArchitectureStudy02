package com.example.movieapplication

import androidx.annotation.DimenRes

interface AppProvider {

    fun getDeviceWidth(): Int

    fun getDimens(@DimenRes dimensId: Int): Float
}