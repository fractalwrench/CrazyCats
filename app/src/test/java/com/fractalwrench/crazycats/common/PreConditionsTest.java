package com.fractalwrench.crazycats.common;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PreConditionsTest {

    @Test(expected = InvocationTargetException.class)
    public void checkNonInstantiation() throws Exception {
        Constructor<?> constructor = PreConditions.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void checkNotNull() throws Exception {
        PreConditions.checkNonNull("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNull() throws Exception {
        PreConditions.checkNonNull(null);
    }

}