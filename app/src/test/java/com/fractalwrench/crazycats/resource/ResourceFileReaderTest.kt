package com.fractalwrench.crazycats.resource

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ResourceFileReaderTest {
    private var reader: StringResourceFileReader? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        reader = StringResourceFileReader()
    }

    @Test
    @Throws(Exception::class)
    fun readResourceAsString() {
        assertEquals(EXPECTED_OUTPUT, reader!!.readResourceAsString(FILENAME))
    }

    @Test(expected = IOException::class)
    @Throws(Exception::class)
    fun resourceNotFound() {
        reader!!.readResourceAsString("totally-legit.jpg")
    }

    companion object {

        private val EXPECTED_OUTPUT = "test"
        private val FILENAME = "test.txt"
    }

}