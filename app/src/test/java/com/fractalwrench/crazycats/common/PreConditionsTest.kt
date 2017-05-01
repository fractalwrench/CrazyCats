package com.fractalwrench.crazycats.common

import org.junit.Test
import java.lang.reflect.InvocationTargetException

class PreConditionsTest {

    @Test(expected = InvocationTargetException::class)
    @Throws(Exception::class)
    fun checkNonInstantiation() {
        val constructor = PreConditions::class.java.declaredConstructors[0]
        constructor.isAccessible = true
        constructor.newInstance()
    }

    @Test
    @Throws(Exception::class)
    fun checkNotNull() {
        PreConditions.checkNonNull("")
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(Exception::class)
    fun checkNull() {
        PreConditions.checkNonNull(null)
    }

}