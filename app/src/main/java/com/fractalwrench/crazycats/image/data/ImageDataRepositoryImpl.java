package com.fractalwrench.crazycats.image.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.injection.DefaultSchedulers;
import com.fractalwrench.crazycats.network.RedditApiService;
import com.fractalwrench.crazycats.network.responses.SubredditListingResponse;
import com.fractalwrench.crazycats.network.responses.SubredditThreadResponse;

import java.util.List;

import io.reactivex.Observable;

public class ImageDataRepositoryImpl implements ImageDataRepository {

    private final RedditApiService redditApiService;
    private final DefaultSchedulers defaultSchedulers;
    private final String subredditPath;
    private final Observable<List<ImageData>> imageFetchObservable;

    public ImageDataRepositoryImpl(RedditApiService redditApiService,
                                   DefaultSchedulers defaultSchedulers, Context context) {
        this.redditApiService = redditApiService;
        this.defaultSchedulers = defaultSchedulers;
        subredditPath = context.getString(R.string.subreddit_path);

        imageFetchObservable = redditApiService.getSubredditListing(subredditPath)
                                               .compose(this::mapSubredditThreads)
                                               .map(ImageData::valueOf)
                                               .toSortedList(ImageData.COMPARATOR)
                                               .toObservable()
                                               .cache()
                                               .compose(defaultSchedulers::apply);
    }

    @Override
    public Observable<List<ImageData>> fetchImageSummaries() {
        return imageFetchObservable;
    }

    @Override
    public Observable<ImageData> fetchImageById(@NonNull String id) {
        return fetchImageSummaries()
                .flatMapIterable(data -> data)
                .filter(imageData -> id.equals(imageData.getId()))
                .firstOrError()
                .toObservable();
    }

    private Observable<SubredditThreadResponse> mapSubredditThreads(
            Observable<SubredditListingResponse> observable) {

        return observable.map(SubredditListingResponse::getData)
                         .map(SubredditListingResponse.ListingData::getChildren)
                         .flatMapIterable(children -> children)
                         .map(SubredditListingResponse.ListingChildren::getData);
    }

}
