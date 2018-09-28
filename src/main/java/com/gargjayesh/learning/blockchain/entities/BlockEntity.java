package com.gargjayesh.learning.blockchain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class BlockEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String hashId;

    @Lob
    private byte[] data;

    public BlockEntity(String hashId, byte[] data) {
        this.hashId = hashId;
        this.data = data;
    }
}
