package com.fractalwrench.crazycats.common;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TextUtilsTest {

    @Test
    public void isEmpty() throws Exception {
        assertTrue(TextUtils.isEmpty(null));
        assertTrue(TextUtils.isEmpty(""));
        assertFalse(TextUtils.isEmpty("test"));
    }

}