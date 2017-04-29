package com.fractalwrench.crazycats.network;

import com.fractalwrench.crazycats.network.responses.SubredditListingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RedditApiService {

    @GET("r/{subreddit}.json")
    Observable<SubredditListingResponse> getSubredditListing(@Path("subreddit") String subreddit);

}
