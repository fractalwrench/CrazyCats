package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.data.ImageSummary;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;

import java.util.List;

import io.reactivex.Observable;

/**
 * A repository which is guaranteed to produce an error for every call, used for testing.
 */
public class MockFailureRepository implements ImageDataRepository {

    public MockFailureRepository(DefaultSchedulers schedulers) {
    }

    @Override
    public Observable<List<ImageSummary>> fetchImageSummaries() {
        return Observable.error(new RuntimeException());
    }

    @Override
    public Observable<ImageDetail> fetchImageById(@NonNull String id) {
        return Observable.error(new RuntimeException());
    }

}
