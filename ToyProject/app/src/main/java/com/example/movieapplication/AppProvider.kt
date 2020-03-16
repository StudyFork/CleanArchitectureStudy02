package com.example.movieapplication

interface AppProvider {

    fun getDeviceWidth(): Int

    fun getDimens(dimensId: Int): Float
}