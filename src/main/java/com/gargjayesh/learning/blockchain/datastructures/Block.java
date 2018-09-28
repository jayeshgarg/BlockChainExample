package com.gargjayesh.learning.blockchain.datastructures;

import com.gargjayesh.learning.blockchain.utils.HashUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Block implements Serializable {

    private static final long serialVersionUID = 6804197095618871550L;
    private String id;
    private final String previousId;
    private final String dataTypeOfData;
    private final Data data;
    private final long creationTimestamp;
    private int nonce = 0;

    public Block(String previousId, String dataTypeOfData, Data data) {
        this.previousId = previousId;
        this.dataTypeOfData = dataTypeOfData;
        this.data = data;
        this.creationTimestamp = System.currentTimeMillis();
        this.id = HashUtil.calculateHash(previousId, dataTypeOfData, data, creationTimestamp, nonce);
    }

    public final void mineBlock(int difficulty) {
        String target = StringUtils.repeat("0", difficulty);
        while (!id.substring(0, difficulty).equals(target)) {
            nonce++;
            id = HashUtil.calculateHash(previousId, dataTypeOfData, data, creationTimestamp, nonce);
        }
    }

    public String getId() {
        return id;
    }

    public String getPreviousId() {
        return previousId;
    }

    public String getDataTypeOfData() {
        return dataTypeOfData;
    }

    public Data getData() {
        return data;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public int getNonce() {
        return nonce;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id='" + getId() + '\'' +
                ", previousId='" + getPreviousId() + '\'' +
                ", dataTypeOfData='" + getDataTypeOfData() + '\'' +
                ", data=" + getData() +
                ", creationTimestamp=" + getCreationTimestamp() +
                ", nonce=" + getNonce() +
                '}';
    }
}
