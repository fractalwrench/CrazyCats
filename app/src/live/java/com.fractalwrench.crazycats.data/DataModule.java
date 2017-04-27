package com.fractalwrench.crazycats.data;


import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.data.ImageSummary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Defines dependencies related to getting data from a Repository implementation
 */
@Singleton
@Module
public class DataModule {

    @Provides
    @Singleton
    ImageDataRepository repository() {
        return new ImageDataRepository() {
            @Override
            public Observable<List<ImageSummary>> fetchImageSummaries(@NonNull String searchTerm) {
                return Observable.just(new ArrayList<>());
            }

            @Override
            public Observable<ImageDetail> fetchImageById(@NonNull String id) {
                return Observable.just(new ImageDetail());
            }
        };
    }

}
