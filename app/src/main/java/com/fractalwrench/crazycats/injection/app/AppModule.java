package com.fractalwrench.crazycats.injection.app;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module for providing the singleton application context
 */
@Singleton
@Module
public class AppModule {

    @NonNull private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return context;
    }

}
