package com.android.data.utils

import android.content.Context
import java.io.IOException

class FileUtils(private val context: Context) {

    fun getJsonDataFromAsset(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }
}
