package com.fractalwrench.crazycats.resource;

import com.squareup.moshi.Moshi;

import java.io.IOException;

/**
 * Reads a resource file and serialises it from a JSON String.
 * @param <T> the type of class to serialise into
 */
public class JsonResourceReader<T> {

    private final StringResourceFileReader resourceFileReader = new StringResourceFileReader();
    private final Moshi moshi;
    private final Class<T> clz;

    public JsonResourceReader(Moshi moshi, Class<T> clz) {
        this.moshi = moshi;
        this.clz = clz;
    }

    public T readResourceAsJson(String resName) throws IOException {
        String json = resourceFileReader.readResourceAsString(resName);
        return moshi.adapter(clz).fromJson(json);
    }

}
