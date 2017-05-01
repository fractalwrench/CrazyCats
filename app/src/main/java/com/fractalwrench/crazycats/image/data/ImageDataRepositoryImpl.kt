package com.fractalwrench.crazycats.image.data

import android.content.Context
import com.fractalwrench.crazycats.R
import com.fractalwrench.crazycats.injection.DefaultSchedulers
import com.fractalwrench.crazycats.network.RedditApiService
import com.fractalwrench.crazycats.network.responses.ImageWrapperResponse
import com.fractalwrench.crazycats.network.responses.SubredditListingResponse
import com.fractalwrench.crazycats.network.responses.SubredditThreadResponse
import io.reactivex.Observable
import java.util.*

class ImageDataRepositoryImpl(private val redditApiService: RedditApiService,
                              private val defaultSchedulers: DefaultSchedulers, context: Context) : ImageDataRepository {

    private val subredditPath: String = context.getString(R.string.subreddit_path)
    private val data = ArrayList<ImageData>()

    override fun fetchImageSummaries(): Observable<List<ImageData>> {
        if (data.isEmpty()) {
            // fetch subreddit listing response and map into ImageData
            return redditApiService.getSubredditListing(subredditPath)
                    .compose<SubredditThreadResponse>({ this.mapSubredditThreads(it) })
                    .map { response ->
                        ImageData(response.id,
                                response.url,
                                getThumbnailPreview(response),
                                response.title)
                    }
                    .toSortedList(IMAGE_DATA_COMPARATOR)
                    .toObservable()
                    .doOnNext({ data.addAll(it) })
                    .compose<List<ImageData>>({ defaultSchedulers.apply(it) })
        } else {
            return Observable.just<List<ImageData>>(data)
                    .compose<List<ImageData>>({ defaultSchedulers.apply(it) })
        }
    }

    override fun fetchImageById(id: String): Observable<ImageData> {
        return fetchImageSummaries().flatMapIterable { data -> data }
                .filter { (id1) -> id == id1 }
                .firstOrError()
                .toObservable()
    }

    private fun mapSubredditThreads(observable: Observable<SubredditListingResponse>): Observable<SubredditThreadResponse> {
        return observable.map( { it.data })
                .map({ it!!.children })
                .flatMapIterable { children -> children }
                .map<SubredditThreadResponse>({ it.data })
    }

    private fun getThumbnailPreview(response: SubredditThreadResponse): String {
        var previewUrl: String = response.url!! // default image if no thumbnail available

        if (response.preview != null) {
            val images = response.preview.images

            if (images != null && !images.isEmpty()) {
                val wrapperResponse = images[0]
                previewUrl = getThumbnailUrl(previewUrl, wrapperResponse)
            }
        }
        return previewUrl
    }

    companion object {

        private val THUMBNAIL_SIZE_THRESHOLD = 300

        private val IMAGE_DATA_COMPARATOR = { lhs: ImageData, rhs: ImageData ->
            lhs.title!!.compareTo(rhs.title!!)
        }

        private fun getThumbnailUrl(url: String, wrapperResponse: ImageWrapperResponse): String {
            val resolutions = wrapperResponse.resolutions

            if (resolutions != null && !resolutions.isEmpty()) {
                Collections.sort(resolutions, compareBy { it.width })

                // TODO production app would use displayMetrics rather than constant size

                for ((url1, width) in resolutions) {
                    if (width >= THUMBNAIL_SIZE_THRESHOLD) {
                        return url1!!.replace("&amp;".toRegex(), "&")
                    }
                }
            }
            return url
        }
    }

}
