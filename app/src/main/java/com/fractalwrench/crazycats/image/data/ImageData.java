package com.fractalwrench.crazycats.image.data;

import com.fractalwrench.crazycats.network.responses.SubredditThreadResponse;

import java.util.Comparator;

public class ImageData {

    public static final Comparator<ImageData> COMPARATOR
            = (lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle());

    private String id;
    private String imageUrl;
    private String thumbnailUrl;
    private String title;

    public ImageData() {
    }

    public ImageData(String id, String imageUrl, String thumbnailUrl, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public static ImageData valueOf(SubredditThreadResponse r) {
        return new ImageData(r.getId(), r.getUrl(), r.getThumbnailPreview(r), r.getTitle());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ImageData{" + "id='" + id + '\'' + ", imageUrl='" + imageUrl + '\'' + ", title='" +
               title + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        ImageData imageData = (ImageData) o;

        return id != null ? id.equals(imageData.id) : imageData.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
