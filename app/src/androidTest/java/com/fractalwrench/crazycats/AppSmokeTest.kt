package com.fractalwrench.crazycats

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Ensures that the application builds and launches on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class AppSmokeTest {

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertTrue(appContext.packageName.startsWith("com.fractalwrench.crazycats"))
    }

}
