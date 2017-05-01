package com.fractalwrench.crazycats.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Enforces use of the [okhttp3.Cache] if there is no connection available.
 */
class NetworkAvailabilityInterceptor internal constructor(context: Context) : Interceptor {

    private val cm: ConnectivityManager

    init {
        this.cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request

        if (isNetworkAvailable) {
            request = chain.request()
        } else { // use cached value if available
            request = chain.request()
                    .newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        return chain.proceed(request)
    }

    private val isNetworkAvailable: Boolean
        get() {
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

}
