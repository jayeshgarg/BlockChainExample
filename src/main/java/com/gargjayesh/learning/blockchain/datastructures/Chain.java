package com.gargjayesh.learning.blockchain.datastructures;

import com.gargjayesh.learning.blockchain.entities.BlockEntity;
import com.gargjayesh.learning.blockchain.exceptions.BlockChainInvalidException;
import com.gargjayesh.learning.blockchain.exceptions.BlockNotFoundException;
import com.gargjayesh.learning.blockchain.repositories.BlockRepository;
import com.gargjayesh.learning.blockchain.utils.HashUtil;
import com.gargjayesh.learning.blockchain.utils.SerializationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Slf4j
public final class Chain {

    private final int difficulty;
    private final BlockRepository blockRepository;

    public Chain(int difficulty, BlockRepository blockRepository) {
        this.difficulty = difficulty;
        this.blockRepository = blockRepository;
    }

    public final Block mineNewBlock(Data data) {
        String dataType = data.getClass().getTypeName();
        long totalBlocks = blockRepository.count();
        String previousHash = "0";
        if (totalBlocks > 0) {
            Optional<BlockEntity> optionalBlockEntity = blockRepository.findById(totalBlocks);
            if (optionalBlockEntity.isPresent()) {
                previousHash = optionalBlockEntity.get().getHashId();
            } else {
                log.error("Block chain is invalid as the count of block is not same as that of id of block at last index.");
                throw new BlockChainInvalidException("Block chain is invalid as the count of block is not same as that of id of block at last index.");
            }
        }
        Block newBlock = new Block(previousHash, dataType, data);
        newBlock.mineBlock(difficulty);
        BlockEntity blockEntity = new BlockEntity(newBlock.getId(), SerializationUtils.objectToByteArray(newBlock));
        blockRepository.save(blockEntity);
        return newBlock;
    }

    public final Block getExistingBlock(Long blockId) {
        Optional<BlockEntity> optionalBlockEntity = blockRepository.findById(blockId);
        if (optionalBlockEntity.isPresent()) {
            return SerializationUtils.byteArrayToObject(optionalBlockEntity.get().getData());
        } else {
            throw new BlockNotFoundException("Block with given id not found. Please check and try again.");
        }
    }

    public final Boolean isBlockChainValid() {
        long blockCount = blockRepository.count();
        boolean result = true;
        if (blockCount != 0) {
            if (blockCount == 1) {
                Block block = SerializationUtils.byteArrayToObject(blockRepository.findById(1L).get().getData());
                if (!isBlockValid(block)) {
                    result = false;
                }
            } else {
                long ctr = 2;
                Block previousBlock = SerializationUtils.byteArrayToObject(blockRepository.findById(ctr - 1).get().getData());
                Block currentBlock = SerializationUtils.byteArrayToObject(blockRepository.findById(ctr).get().getData());
                while (ctr <= blockCount) {
                    if (!isBlockValid(previousBlock) || !isBlockValid(currentBlock)) {
                        result = false;
                        break;
                    }
                    if (!currentBlock.getPreviousId().equalsIgnoreCase(previousBlock.getId())) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private Boolean isBlockValid(Block block) {
        String originalHash = block.getId();
        String calculatedHash = HashUtil.calculateHash(
                block.getPreviousId(),
                block.getDataTypeOfData(),
                block.getData(),
                block.getCreationTimestamp(),
                block.getNonce()
        );
        String hashTarget = StringUtils.repeat("0", difficulty);
        return originalHash.equalsIgnoreCase(calculatedHash) &&//the hash stored as key is different from generated one
                block.getId().substring(0, difficulty).equals(hashTarget);//this hash was not mined
    }
}
