package com.fractalwrench.crazycats;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Ensures that the application builds and launches on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class AppSmokeTest {

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertTrue(appContext.getPackageName().startsWith("com.fractalwrench.crazycats"));
    }

}
