package com.fractalwrench.crazycats.injection.app;


import com.fractalwrench.crazycats.injection.DefaultSchedulers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Defines the default scheduler threads for Observables. These can be swapped out when testing.
 */
@Singleton
@Module
public class SchedulerModule {

    @Provides
    @Singleton
    DefaultSchedulers providesAppSchedulers() {
        return new DefaultSchedulers(Schedulers.io(),
                                     AndroidSchedulers.mainThread());
    }

}
