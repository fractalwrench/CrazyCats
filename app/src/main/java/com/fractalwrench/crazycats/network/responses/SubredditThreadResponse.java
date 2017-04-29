package com.fractalwrench.crazycats.network.responses;

import java.util.List;

public class SubredditThreadResponse {

    private String id;
    private String url;
    private String title;
    private Preview preview;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public static class Preview {
        private List<ImageWrapperResponse> images;

        public List<ImageWrapperResponse> getImages() {
            return images;
        }

        public void setImages(List<ImageWrapperResponse> images) {
            this.images = images;
        }
    }

    @Override
    public String toString() {
        return "SubredditThreadResponse{" + "id='" + id + '\'' + ", url='" + url + '\'' +
               ", title='" + title + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        SubredditThreadResponse that = (SubredditThreadResponse) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
