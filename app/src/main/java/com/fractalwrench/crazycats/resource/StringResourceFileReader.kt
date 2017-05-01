package com.fractalwrench.crazycats.resource


import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Reads a resource file as a string
 */
internal class StringResourceFileReader {

    @Throws(IOException::class)
    fun readResourceAsString(resName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream(resName) ?: throw IOException(String.format("Failed to find resource '%s'", resName))

        inputStream.use { inputStream ->
            val br = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            val sb = StringBuilder()

            line = br.readLine()

            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
            return sb.toString()
        }
    }

}
