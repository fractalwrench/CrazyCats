package com.fractalwrench.crazycats.data;


import android.content.Context;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageDataRepositoryImpl;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.fractalwrench.crazycats.network.RedditApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Defines dependencies related to getting data from a Repository implementation
 */
@Singleton
@Module
public class DataModule {

    @Provides
    @Singleton
    ImageDataRepository repository(RedditApiService redditApiService,
                                   DefaultSchedulers defaultSchedulers, Context context) {
        return new ImageDataRepositoryImpl(redditApiService, defaultSchedulers, context);
    }

}
