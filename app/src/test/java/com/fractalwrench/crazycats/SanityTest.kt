package com.fractalwrench.crazycats

import org.junit.Test

import org.junit.Assert.assertEquals

/**
 * Sanity test to ensure that JUnit is setup correctly
 */
class SanityTest {

    @Test
    @Throws(Exception::class)
    fun checkAddition() {
        assertEquals(4, (2 + 2).toLong())
    }

}