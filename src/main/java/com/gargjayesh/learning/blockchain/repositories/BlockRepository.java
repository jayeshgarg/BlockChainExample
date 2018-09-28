package com.gargjayesh.learning.blockchain.repositories;

import com.gargjayesh.learning.blockchain.entities.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, Long> {
    BlockEntity findByHashId(String hashId);
}
