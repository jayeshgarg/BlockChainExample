package com.gargjayesh.learning.blockchain.utils;

import com.gargjayesh.learning.blockchain.datastructures.Data;
import com.gargjayesh.learning.blockchain.exceptions.HashAlgoNotPresentException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.util.encoders.Hex;

@Slf4j
public final class HashUtil {
    private HashUtil() {
    }

    public static String calculateHash(String previousId, String dataType, Data data, long timeStamp, int nonce) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            String messageToBeDigested = previousId + dataType + Hex.encode(data.toString().getBytes()) + timeStamp + nonce;
            return new String(Hex.encode(digester.digest(messageToBeDigested.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            log.error("Invalid hashing algorithm selected.", e);
            throw new HashAlgoNotPresentException("Choose correct hashing algorithm for this to work correctly.");
        }
    }
}
