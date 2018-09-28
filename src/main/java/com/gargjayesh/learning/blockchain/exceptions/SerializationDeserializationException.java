package com.gargjayesh.learning.blockchain.exceptions;

public class SerializationDeserializationException extends BlockChainException {
    public SerializationDeserializationException() {
        super();
    }

    public SerializationDeserializationException(String message) {
        super(message);
    }

    public SerializationDeserializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationDeserializationException(Throwable cause) {
        super(cause);
    }
}
