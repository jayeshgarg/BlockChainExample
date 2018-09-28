package com.gargjayesh.learning.blockchain.exceptions;

public class BlockChainInvalidException extends BlockChainException {
    public BlockChainInvalidException() {
        super();
    }

    public BlockChainInvalidException(String message) {
        super(message);
    }

    public BlockChainInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockChainInvalidException(Throwable cause) {
        super(cause);
    }
}
