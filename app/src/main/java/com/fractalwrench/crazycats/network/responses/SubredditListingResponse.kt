package com.fractalwrench.crazycats.network.responses

data class SubredditListingResponse(val data: ListingData? = null)
data class ListingData(val children: List<ListingChildren>?)
data class ListingChildren(val data: SubredditThreadResponse?)
