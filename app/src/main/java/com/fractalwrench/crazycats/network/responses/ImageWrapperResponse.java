package com.fractalwrench.crazycats.network.responses;

import java.util.List;

public class ImageWrapperResponse {

    private ImageResponse source;
    private List<ImageResponse> resolutions;

    public ImageResponse getSource() {
        return source;
    }

    public void setSource(ImageResponse source) {
        this.source = source;
    }

    public List<ImageResponse> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<ImageResponse> resolutions) {
        this.resolutions = resolutions;
    }
}
