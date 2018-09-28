package com.gargjayesh.learning.blockchain;

import com.gargjayesh.learning.blockchain.datastructures.Block;
import com.gargjayesh.learning.blockchain.datastructures.Chain;
import com.gargjayesh.learning.blockchain.datastructures.subdatastructures.DocumentInformation;
import com.gargjayesh.learning.blockchain.datastructures.subdatastructures.UserInformation;
import com.gargjayesh.learning.blockchain.repositories.BlockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CLIRunner implements CommandLineRunner {

    private static final int DIFFICULTY = 3;

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public void run(String... args) {
        log.debug("Starting block chain application...");

        log.debug("Init block chain...");
        Chain chain = new Chain(DIFFICULTY, blockRepository);
        log.debug("Block chain initialized successfully...");

        log.debug("Adding 10 user blocks to block chain...");
        for (int i = 1; i <= 10; i++) {
            Block block = chain.mineNewBlock(new UserInformation());
            log.debug("Newly mined and saved block in chain is : {} with hash : {}", i, block.getId());
        }
        log.debug("Added 10 user blocks to block chain...");

        log.debug("Adding 10 document blocks to block chain...");
        for (int i = 11; i <= 20; i++) {
            Block block = chain.mineNewBlock(new DocumentInformation());
            log.debug("Newly mined and saved block in chain is : {} with hash : {}", i, block.getId());
        }
        log.debug("Added 10 document blocks to block chain...");

        log.debug("End of block chain generation...");

        log.debug("Checking validation of block chain...");
        log.debug("Status = {}", chain.isBlockChainValid());

        long myBlock = 3;
        log.debug("Fetching user block {} from blockchain...", myBlock);
        Block receivedBlock = chain.getExistingBlock(myBlock);
        log.debug("Received block data is...");
        printBlockData(receivedBlock);

        myBlock = 13;
        log.debug("Fetching user block {} from blockchain...", myBlock);
        receivedBlock = chain.getExistingBlock(myBlock);
        log.debug("Received block data is...");
        printBlockData(receivedBlock);

        log.debug("Terminating application now...");
        System.exit(0);
    }

    private void printBlockData(Block block) {
        if (block.getData() instanceof UserInformation) {
            log.debug("User information = {}", UserInformation.class.cast(block.getData()));
        } else if (block.getData() instanceof DocumentInformation) {
            log.debug("Document information = {}", DocumentInformation.class.cast(block.getData()));
        } else {
            log.error("Incorrect data type found in DB. This is not a block data. DB may have gone inconsistent...");
        }
    }
}
