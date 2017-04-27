package com.fractalwrench.crazycats.resource;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class ResourceFileReaderTest {

    private static final String EXPECTED_OUTPUT = "test";
    private static final String FILENAME = "test.txt";
    private StringResourceFileReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new StringResourceFileReader();
    }

    @Test
    public void readResourceAsString() throws Exception {
        assertEquals(EXPECTED_OUTPUT, reader.readResourceAsString(FILENAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullName() throws Exception {
        reader.readResourceAsString(null);
    }

    @Test(expected = IOException.class)
    public void resourceNotFound() throws Exception {
        reader.readResourceAsString("totally-legit.jpg");
    }

}