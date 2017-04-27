package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.data.ImageSummary;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.fractalwrench.crazycats.resource.JsonResourceReader;
import com.fractalwrench.crazycats.resource.ResourcePaths;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

/**
 * A repository which is guaranteed to succeed for every call, used for testing.
 */
public class MockSuccessRepository implements ImageDataRepository {

    private final DefaultSchedulers schedulers;
    private final com.fractalwrench.crazycats.image.data.ImageDetail imageDetail;
    private final List<ImageSummary> summaryList;

    public MockSuccessRepository(DefaultSchedulers schedulers) throws IOException {
        this.schedulers = schedulers;
        Moshi moshi = new Moshi.Builder().build();

        JsonResourceReader<ImageSummary> summaryReader
                = new JsonResourceReader<>(moshi, ImageSummary.class);

        JsonResourceReader<ImageDetail> detailReader
                = new JsonResourceReader<>(moshi, ImageDetail.class);

        ImageSummary summary = summaryReader.readResourceAsJson(ResourcePaths.IMAGE_SUMMARY);
        summaryList = Collections.singletonList(summary);
        imageDetail = detailReader.readResourceAsJson(ResourcePaths.IMAGE_DETAIL);
    }

    @Override
    public Observable<List<ImageSummary>> fetchImageSummaries() {
        return Observable.just(summaryList)
                         .compose(schedulers::apply);
    }

    @Override
    public Observable<ImageDetail> fetchImageById(@NonNull String id) {
        return Observable.just(imageDetail)
                         .compose(schedulers::apply);
    }

}
