package com.gargjayesh.learning.blockchain.utils;

import com.gargjayesh.learning.blockchain.datastructures.Block;
import com.gargjayesh.learning.blockchain.exceptions.SerializationDeserializationException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Slf4j
public final class SerializationUtils {
    private SerializationUtils() {
    }

    public static final Block byteArrayToObject(byte[] data) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Block blockObject = (Block) objectInputStream.readObject();
            objectInputStream.close();
            return blockObject;
        } catch (ClassNotFoundException | IOException e) {
            log.error("Unable to deserialize the data into object", e);
            throw new SerializationDeserializationException("Unable to deserialize the data into object");
        }
    }

    public static final byte[] objectToByteArray(Serializable object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("Unable to serialize the object into byte array", e);
            throw new SerializationDeserializationException("Unable to serialize the data into byte array");
        }
    }
}
