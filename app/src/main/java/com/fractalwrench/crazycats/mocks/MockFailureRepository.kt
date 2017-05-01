package com.fractalwrench.crazycats.mocks

import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.data.ImageDataRepository

import io.reactivex.Observable

/**
 * A repository which is guaranteed to produce an error for every call, used for testing.
 */
class MockFailureRepository : ImageDataRepository {

    override fun fetchImageSummaries(): Observable<List<ImageData>> {
        return Observable.error(RuntimeException())
    }

    override fun fetchImageById(id: String): Observable<ImageData> {
        return Observable.error(RuntimeException())
    }

}
