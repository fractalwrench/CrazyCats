package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.data.ImageDataRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * A repository which is guaranteed to produce an error for every call, used for testing.
 */
public class MockFailureRepository implements ImageDataRepository {

    @Override
    public Observable<List<ImageData>> fetchImageSummaries() {
        return Observable.error(new RuntimeException());
    }

    @Override
    public Observable<ImageData> fetchImageById(@NonNull String id) {
        return Observable.error(new RuntimeException());
    }

}
