package com.fractalwrench.crazycats.common;

public final class PreConditions {

    private PreConditions() {
        throw new AssertionError();
    }

    public static void checkNonNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null!");
        }
    }

}
