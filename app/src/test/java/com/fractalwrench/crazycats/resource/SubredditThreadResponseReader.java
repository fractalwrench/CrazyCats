package com.fractalwrench.crazycats.resource;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.network.responses.ImageResponse;
import com.fractalwrench.crazycats.network.responses.ImageWrapperResponse;
import com.fractalwrench.crazycats.network.responses.SubredditThreadResponse;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

public class SubredditThreadResponseReader {

    @Test
    public void testJsonSerialisation() throws IOException {
        Moshi moshi = new Moshi.Builder().build();

        JsonResourceReader<SubredditThreadResponse> reader
                = new JsonResourceReader<>(moshi, SubredditThreadResponse.class);

        SubredditThreadResponse item = reader.readResourceAsJson(
                ResourcePaths.Companion.getTHREAD_ITEM());
        checkTopLevelFields(item);
        List<ImageWrapperResponse> images = checkPreviewField(item);

        ImageWrapperResponse wrapperResponse = images.get(0);
        assertNotNull(wrapperResponse);

        ImageResponse source = wrapperResponse.getSource();
        assertNotNull(source);

        List<ImageResponse> resolutions = wrapperResponse.getResolutions();
        assertNotNull(source);
        assertFalse(resolutions.isEmpty());
    }

    @NonNull
    private List<ImageWrapperResponse> checkPreviewField(SubredditThreadResponse item) {
        SubredditThreadResponse.Preview preview = item.getPreview();
        assertNotNull(preview);

        List<ImageWrapperResponse> images = preview.getImages();
        assertNotNull(images);
        assertFalse(images.isEmpty());
        return images;
    }

    private void checkTopLevelFields(SubredditThreadResponse item) {
        assertNotNull(item);

        assertEquals("68297n", item.getId());
        assertEquals("https://i.redd.it/yfqgm6yycauy.jpg", item.getUrl());
        assertEquals("This is my baby girl, having her first nap at her new place", item.getTitle());
    }


}
