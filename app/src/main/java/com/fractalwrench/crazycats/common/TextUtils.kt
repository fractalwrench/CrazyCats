package com.fractalwrench.crazycats.common

class TextUtils private constructor() {

    init {
        throw AssertionError()
    }

    companion object {

        /**
         * Returns true if the [CharSequence] is empty or null

         * @param text the input text
         * *
         * @return true if empty
         */
        fun isEmpty(text: CharSequence?): Boolean {
            return text == null || text.isEmpty()
        }
    }

}
