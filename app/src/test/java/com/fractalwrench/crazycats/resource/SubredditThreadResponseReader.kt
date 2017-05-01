package com.fractalwrench.crazycats.resource

import com.fractalwrench.crazycats.network.responses.ImageWrapperResponse
import com.fractalwrench.crazycats.network.responses.SubredditThreadResponse
import com.squareup.moshi.Moshi
import junit.framework.Assert.*
import org.junit.Test
import java.io.IOException

class SubredditThreadResponseReader {

    @Test
    @Throws(IOException::class)
    fun testJsonSerialisation() {
        val moshi = Moshi.Builder().build()

        val reader = JsonResourceReader(moshi, SubredditThreadResponse::class.java)

        val item = reader.readResourceAsJson(
                ResourcePaths.THREAD_ITEM)
        checkTopLevelFields(item)
        val images = checkPreviewField(item)

        val wrapperResponse = images[0]
        assertNotNull(wrapperResponse)

        val source = wrapperResponse.source
        assertNotNull(source)

        val resolutions = wrapperResponse.resolutions
        assertNotNull(source)
        assertFalse(resolutions!!.isEmpty())
    }

    private fun checkPreviewField(item: SubredditThreadResponse): List<ImageWrapperResponse> {
        val preview = item.preview
        assertNotNull(preview)

        val images = preview!!.images!!
        assertNotNull(images)
        assertFalse(images.isEmpty())
        return images
    }

    private fun checkTopLevelFields(item: SubredditThreadResponse) {
        assertNotNull(item)

        assertEquals("68297n", item.id)
        assertEquals("https://i.redd.it/yfqgm6yycauy.jpg", item.url)
        assertEquals("This is my baby girl, having her first nap at her new place", item.title)
    }


}
