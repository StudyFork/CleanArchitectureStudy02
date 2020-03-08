package com.template.nanamare.utils

import android.content.Context
import java.io.IOException

class AssetFileReader(private val context: Context) {

    fun readFile(fileName: String): String? {
        return try {
            context.assets.open(fileName).let {
                val buffer = ByteArray(it.available())
                it.read(buffer)
                it.close()
                String(buffer, Charsets.UTF_8)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}