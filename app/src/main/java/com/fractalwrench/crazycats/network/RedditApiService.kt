package com.fractalwrench.crazycats.network

import com.fractalwrench.crazycats.network.responses.SubredditListingResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditApiService {

    @GET("r/{subreddit}.json")
    fun getSubredditListing(@Path("subreddit") subreddit: String): Observable<SubredditListingResponse>

}
