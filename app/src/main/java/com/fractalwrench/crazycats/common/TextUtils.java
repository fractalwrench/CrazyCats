package com.fractalwrench.crazycats.common;

public final class TextUtils {

    private TextUtils() {
        throw new AssertionError();
    }

    /**
     * Returns true if the {@link CharSequence} is empty or null
     *
     * @param text the input text
     * @return true if empty
     */
    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

}
