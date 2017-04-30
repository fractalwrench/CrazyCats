package com.fractalwrench.crazycats.network.responses;

import java.util.Collections;
import java.util.List;

public class SubredditThreadResponse {

    private static final int THUMBNAIL_SIZE_THRESHOLD = 300;

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

    public String getThumbnailPreview() {
        String previewUrl = url; // default image if no thumbnail available

        if (preview != null) {
            List<ImageWrapperResponse> images = preview.getImages();

            if (images != null && !images.isEmpty()) {
                ImageWrapperResponse wrapperResponse = images.get(0);
                previewUrl = getThumbnailUrl(previewUrl, wrapperResponse);
            }
        }
        return previewUrl;
    }

    private static String getThumbnailUrl(String url, ImageWrapperResponse wrapperResponse) {
        List<ImageResponse> resolutions = wrapperResponse.getResolutions();

        if (resolutions != null && !resolutions.isEmpty()) {
            Collections.sort(resolutions, (lhs, rhs) -> lhs.getWidth() - rhs.getWidth());

            // TODO production app would use displayMetrics rather than constant size

            for (ImageResponse resolution : resolutions) {
                if (resolution.getWidth() >= THUMBNAIL_SIZE_THRESHOLD) {
                    return resolution.getUrl().replaceAll("&amp;", "&");
                }
            }
        }
        return url;
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
