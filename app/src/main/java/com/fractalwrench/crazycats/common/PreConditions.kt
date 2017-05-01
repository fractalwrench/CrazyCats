package com.fractalwrench.crazycats.common

class PreConditions private constructor() {

    init {
        throw AssertionError()
    }

    companion object {

        fun checkNonNull(`object`: Any?) {
            if (`object` == null) {
                throw IllegalArgumentException("Object cannot be null!")
            }
        }
    }

}
