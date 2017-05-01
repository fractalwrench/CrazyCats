package com.fractalwrench.crazycats.image.data

import io.reactivex.Observable

/**
 * Uses the repository pattern to fetch images of Cats. This would allow for the data source to
 * be obscured, or for extra operations to be added on (e.g. querying a DB)
 */
interface ImageDataRepository {

    /**
     * Fetches a [List] of [ImageData] which match the given search term

     * @return an observable which will complete on fetching any matching images
     */
    fun fetchImageSummaries(): Observable<List<ImageData>>

    /**
     * Fetches a [ImageData] by its id

     * @param id must be non-null
     * *
     * @return an observable which will complete on fetching the image (if it exists)
     */
    fun fetchImageById(id: String): Observable<ImageData>

}
