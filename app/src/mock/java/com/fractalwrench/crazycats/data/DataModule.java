package com.fractalwrench.crazycats.data;


import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.fractalwrench.crazycats.mocks.MockSuccessRepository;

import java.io.IOException;

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
    ImageDataRepository repository(DefaultSchedulers defaultSchedulers) {
        try {
            return new MockSuccessRepository(defaultSchedulers);
        }
        catch (IOException e) {
            return null;
        }
    }

}
