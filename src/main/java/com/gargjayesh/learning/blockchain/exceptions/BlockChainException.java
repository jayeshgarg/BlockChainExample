package com.gargjayesh.learning.blockchain.exceptions;

public class BlockChainException extends RuntimeException {
    public BlockChainException() {
        super();
    }

    public BlockChainException(String message) {
        super(message);
    }

    public BlockChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockChainException(Throwable cause) {
        super(cause);
    }
}
