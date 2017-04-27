package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.data.ImageSummary;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.fractalwrench.crazycats.resource.JsonResourceReader;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * A repository which is guaranteed to succeed for every call, used for testing.
 */
public class MockSuccessRepository implements ImageDataRepository {

    private final DefaultSchedulers schedulers;
    private final com.fractalwrench.crazycats.image.data.ImageDetail imageDetail = new ImageDetail();
    private final List<ImageSummary> summaryList = new ArrayList<>();

    public MockSuccessRepository(DefaultSchedulers schedulers) throws IOException {
        this.schedulers = schedulers;
        Moshi moshi = new Moshi.Builder().build();
        JsonResourceReader<ImageDetail> detailReader = new JsonResourceReader<>(moshi,
                                                                                ImageDetail.class);
//        JsonResourceReader<ImageSummaryWrapper> listReader = new JsonResourceReader<>(moshi,
//                                                                                      ImageSummaryWrapper.class);
//
//        imageDetail = detailReader.readResourceAsJson(ResourcePaths.Image_DETAIL);
//        ImageSummaryWrapper wrapper = listReader.readResourceAsJson(ResourcePaths.Image_SEARCH);
//        summaryList = wrapper.getResults();
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
