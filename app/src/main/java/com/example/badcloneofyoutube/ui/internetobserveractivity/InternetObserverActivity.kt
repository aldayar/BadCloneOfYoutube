package com.example.badcloneofyoutube.ui.internetobserveractivity

import android.content.Context
import android.net.ConnectivityManager

class InternetObserverActivity(private val context: Context, private val callback: InternetCallback) {

    interface InternetCallback {
        fun onInternetAvailable()
        fun onInternetUnavailable()
    }

    fun checkInternet() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            callback.onInternetAvailable()
        } else {
            callback.onInternetUnavailable()
        }
    }
}
