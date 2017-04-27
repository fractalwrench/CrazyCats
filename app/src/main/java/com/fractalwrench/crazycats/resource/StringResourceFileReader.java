package com.fractalwrench.crazycats.resource;


import com.fractalwrench.crazycats.common.PreConditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads a resource file as a string
 */
class StringResourceFileReader {

    String readResourceAsString(String resName) throws IOException {
        PreConditions.checkNonNull(resName);
        InputStream inputStream = getClass().getClassLoader()
                                            .getResourceAsStream(resName);

        if (inputStream == null) {
            throw new IOException(String.format("Failed to find resource '%s'", resName));
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        finally {
            inputStream.close();
        }
    }

}
