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
     * Fetches a {@link List} of {@link ImageSummary} which match the given search term
     *
     * @param searchTerm must be non-null
     * @return an observable which will complete on fetching any matching images
     */
    Observable<List<ImageSummary>> fetchImageSummaries(@NonNull String searchTerm);

    /**
     * Fetches a {@link ImageDetail} by its id
     *
     * @param id must be non-null
     * @return an observable which will complete on fetching the image (if it exists)
     */
    Observable<ImageDetail> fetchImageById(@NonNull String id);

}
