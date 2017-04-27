package com.fractalwrench.crazycats.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Enforces use of the {@link okhttp3.Cache} if there is no connection available.
 */
public class NetworkAvailabilityInterceptor implements Interceptor {

    private final ConnectivityManager cm;

    NetworkAvailabilityInterceptor(Context context) {
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request;

        if (isNetworkAvailable()) {
            request = chain.request();
        }
        else { // use cached value if available
            request = chain.request()
                           .newBuilder()
                           .cacheControl(CacheControl.FORCE_CACHE)
                           .build();
        }
        return chain.proceed(request);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
