package com.fractalwrench.crazycats.network;

import android.content.Context;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

@Singleton
@Module
public class NetworkModule {

    private static final int CACHE_SIZE_MB = 8 * 1024 * 1024;
    private static final String CACHE_NAME = "CatCache";

    private final boolean debug;

    public NetworkModule(boolean debug) {
        this.debug = debug;
    }

    @Provides
    @Singleton
    RedditApiService omdbApiService(Context context, DefaultSchedulers defaultSchedulers,
                                    OkHttpClient okHttpClient) {
        String baseUrl = context.getString(R.string.base_api_url);
        RxJava2CallAdapterFactory factory = RxJava2CallAdapterFactory.createWithScheduler(
                defaultSchedulers.io());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                                  .client(okHttpClient)
                                                  .addConverterFactory(
                                                          MoshiConverterFactory.create())
                                                  .addCallAdapterFactory(factory)
                                                  .build();

        return retrofit.create(RedditApiService.class);
    }

    @Provides
    @Singleton
    Cache cache(Context context) {
        File cache = new File(context.getCacheDir(), CACHE_NAME);
        return new Cache(cache, CACHE_SIZE_MB);
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient(Cache cache,
                              NetworkAvailabilityInterceptor networkAvailabilityInterceptor,
                              HttpLoggingInterceptor httpLoggingInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.cache(cache)
                      .addInterceptor(httpLoggingInterceptor)
                      .addInterceptor(networkAvailabilityInterceptor)
                      .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        Level level = debug ? Level.BODY : Level.NONE; // disable logging in prod
        interceptor.setLevel(level);
        return interceptor;
    }

    @Provides
    @Singleton
    NetworkAvailabilityInterceptor networkAvailabilityInterceptor(Context context) {
        return new NetworkAvailabilityInterceptor(context);
    }

}
