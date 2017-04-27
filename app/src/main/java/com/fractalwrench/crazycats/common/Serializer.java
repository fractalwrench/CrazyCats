package com.fractalwrench.crazycats.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Adapted from http://stackoverflow.com/a/3840817/5144991
 */
public final class Serializer {

    private Serializer() {
        throw new AssertionError();
    }

    private static <T extends Serializable> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return baos.toByteArray();
    }

    private static <T extends Serializable> T deserialize(byte[] b, Class<T> cl) throws IOException,
                                                                                        ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        return cl.cast(o);
    }


    /**
     * Serialises an object then immediately deserialises it.
     */
    public static <T extends Serializable> T serializeAndDeserialize(T object, Class<T> clz) throws
                                                                                             IOException,
                                                                                             ClassNotFoundException {
        byte[] bytes = Serializer.serialize(object);
        return Serializer.deserialize(bytes, clz);
    }

}
