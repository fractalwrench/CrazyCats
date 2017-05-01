package com.fractalwrench.crazycats.resource

import com.squareup.moshi.Moshi

import java.io.IOException

/**
 * Reads a resource file and serialises it from a JSON String.
 * @param <T> the type of class to serialise into
</T> */
class JsonResourceReader<T>(private val moshi: Moshi, private val clz: Class<T>) {

    private val resourceFileReader = StringResourceFileReader()

    @Throws(IOException::class)
    fun readResourceAsJson(resName: String): T {
        val json = resourceFileReader.readResourceAsString(resName)
        return moshi.adapter(clz).fromJson(json)
    }

}
