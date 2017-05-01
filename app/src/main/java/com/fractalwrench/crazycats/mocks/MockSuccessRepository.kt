package com.fractalwrench.crazycats.mocks

import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.data.ImageDataRepository
import com.fractalwrench.crazycats.injection.DefaultSchedulers
import io.reactivex.Observable
import java.io.IOException

/**
 * A repository which is guaranteed to succeed for every call, used for testing.
 */
class MockSuccessRepository @Throws(IOException::class)
constructor(private val schedulers: DefaultSchedulers) : ImageDataRepository {

    private val imageData: ImageData

    init {
        imageData = generateFakeData()
    }

    private fun generateFakeData(): ImageData {
        return ImageData("testId", "https://i.redd.it/yfqgm6yycauy.jpg",
                "https://a.thumbs.redditmedia" + ".com/przYHKLLiwj_RKcYpd3NL08pLAbO-dPVYaalv2TZ4t4.jpg",
                "My Awesome Cat")
    }

    override fun fetchImageSummaries(): Observable<List<ImageData>> {
        return Observable.just(listOf(imageData))
                .compose<List<ImageData>>({ schedulers.apply(it) })
    }

    override fun fetchImageById(id: String): Observable<ImageData> {
        return Observable.just(imageData)
                .compose<ImageData>({ schedulers.apply(it) })
    }

}
