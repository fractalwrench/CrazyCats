package com.fractalwrench.crazycats.common

import org.junit.Test

import junit.framework.Assert.assertTrue
import org.junit.Assert.assertFalse

class TextUtilsTest {

    @Test
    @Throws(Exception::class)
    fun isEmpty() {
        assertTrue(TextUtils.isEmpty(null))
        assertTrue(TextUtils.isEmpty(""))
        assertFalse(TextUtils.isEmpty("test"))
    }

}