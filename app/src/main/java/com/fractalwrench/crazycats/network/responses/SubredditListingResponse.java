package com.fractalwrench.crazycats.network.responses;

import java.util.List;

public class SubredditListingResponse {

    private ListingData data;

    public static class ListingData {
        private List<ListingChildren> children;

        public List<ListingChildren> getChildren() {
            return children;
        }

        public void setChildren(List<ListingChildren> children) {
            this.children = children;
        }
    }

    public static class ListingChildren {
        private List<SubredditThreadResponse> data;

        public List<SubredditThreadResponse> getData() {
            return data;
        }

        public void setData(List<SubredditThreadResponse> data) {
            this.data = data;
        }
    }

    public ListingData getData() {
        return data;
    }

    public void setData(ListingData data) {
        this.data = data;
    }
}
