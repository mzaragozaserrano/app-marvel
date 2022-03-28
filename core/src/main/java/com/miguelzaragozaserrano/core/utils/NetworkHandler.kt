package com.miguelzaragozaserrano.core.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkHandler(private val context: Context) {
    fun isConnected(): Boolean {
        val connectivityManager =
            this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities != null
    }
}