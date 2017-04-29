package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

/**
 * A repository which is guaranteed to succeed for every call, used for testing.
 */
public class MockSuccessRepository implements ImageDataRepository {

    private final DefaultSchedulers schedulers;
    private final ImageData imageData;

    public MockSuccessRepository(DefaultSchedulers schedulers) throws IOException {
        this.schedulers = schedulers;
        imageData = generateFakeData();
    }

    private ImageData generateFakeData() {
        ImageData imageData = new ImageData();
        imageData.setId("testId");
        imageData.setImageUrl("https://i.redd.it/yfqgm6yycauy.jpg");
        imageData.setThumbnailUrl("https://a.thumbs.redditmedia.com/przYHKLLiwj_RKcYpd3NL08pLAbO-dPVYaalv2TZ4t4.jpg");
        imageData.setTitle("My Awesome Cat");
        return imageData;
    }

    @Override
    public Observable<List<ImageData>> fetchImageSummaries() {
        return Observable.just(Collections.singletonList(imageData))
                         .compose(schedulers::apply);
    }

    @Override
    public Observable<ImageData> fetchImageById(@NonNull String id) {
        return Observable.just(imageData)
                         .compose(schedulers::apply);
    }

}
