package com.fractalwrench.crazycats.image.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;

/**
 * Uses the repository pattern to fetch images of Cats. This would allow for the data source to
 * be obscured, or for extra operations to be added on (e.g. querying a DB)
 */
public interface ImageDataRepository {

    /**
     * Fetches a {@link List} of {@link ImageData} which match the given search term
     *
     * @return an observable which will complete on fetching any matching images
     */
    Observable<List<ImageData>> fetchImageSummaries();

    /**
     * Fetches a {@link ImageData} by its id
     *
     * @param id must be non-null
     * @return an observable which will complete on fetching the image (if it exists)
     */
    Observable<ImageData> fetchImageById(@NonNull String id);

}
