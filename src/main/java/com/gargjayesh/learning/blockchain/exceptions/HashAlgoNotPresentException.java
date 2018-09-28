package com.gargjayesh.learning.blockchain.exceptions;

public class HashAlgoNotPresentException extends BlockChainException {
    public HashAlgoNotPresentException() {
        super();
    }

    public HashAlgoNotPresentException(String message) {
        super(message);
    }

    public HashAlgoNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashAlgoNotPresentException(Throwable cause) {
        super(cause);
    }
}
