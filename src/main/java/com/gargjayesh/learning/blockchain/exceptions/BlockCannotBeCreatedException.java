package com.gargjayesh.learning.blockchain.exceptions;

public class BlockCannotBeCreatedException extends BlockChainException {
    public BlockCannotBeCreatedException() {
        super();
    }

    public BlockCannotBeCreatedException(String message) {
        super(message);
    }

    public BlockCannotBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockCannotBeCreatedException(Throwable cause) {
        super(cause);
    }
}
